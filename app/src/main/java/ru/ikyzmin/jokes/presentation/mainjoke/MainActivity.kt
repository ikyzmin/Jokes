package ru.ikyzmin.jokes.presentation.mainjoke

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.domain.models.JokeType
import ru.ikyzmin.jokes.presentation.ComponentHolder
import ru.ikyzmin.jokes.presentation.history.HistoryActivity

class MainActivity : AppCompatActivity() {

    private val component = ComponentHolder.getComponent()
    private val viewModel: MainViewModel by viewModels { component.mainViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val newJokeButton = findViewById<Button>(R.id.new_joke)
        val historyButton = findViewById<Button>(R.id.history_button)
        val jokeTextView = findViewById<TextView>(R.id.joke_text)
        val jokeSetupTextView = findViewById<TextView>(R.id.setup_text)
        val jokeDeliveryTextView = findViewById<TextView>(R.id.delivery_text)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val twolineJokeLayout = findViewById<View>(R.id.twoline_joke_layout)

        historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        newJokeButton.setOnClickListener {
            viewModel.loadJoke()
        }
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is MainState.Content -> {
                    when (state.joke.type) {
                        JokeType.single -> {
                            jokeTextView.visibility = View.VISIBLE
                            twolineJokeLayout.visibility = View.GONE
                            jokeTextView.text = state.joke.joke.orEmpty()
                        }

                        JokeType.twopart -> {
                            jokeTextView.visibility = View.GONE
                            twolineJokeLayout.visibility = View.VISIBLE
                            jokeSetupTextView.text = state.joke.setup.orEmpty()
                            jokeDeliveryTextView.text = state.joke.delivery.orEmpty()
                        }

                        else -> {/* no-op */
                        }
                    }
                    progressBar.visibility = View.GONE
                    newJokeButton.isEnabled = true
                }

                MainState.Error -> TODO()
                MainState.Loading -> {
                    newJokeButton.isEnabled = false
                    progressBar.visibility = View.VISIBLE
                }
            }

        }
    }
}
