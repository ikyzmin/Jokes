package ru.ikyzmin.jokes.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.ikyzmin.jokes.domain.models.JokeType
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.data.db.JokeEntity
import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke

class MainActivity : AppCompatActivity() {

    private val component = ComponentHolder.getComponent()
    private val jokeUseCase = component.getJokesUseCase
    private val saveJokeUseCase = component.saveJokeUseCase

    private val coroutineScope = this.lifecycleScope

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
            newJokeButton.isEnabled = false
            progressBar.visibility = View.VISIBLE
            jokeUseCase.getJokes(blacklist = "", callback = object : Callback {
                override fun onSuccess(joke: Joke) {
                    saveJoke(joke)
                    when (joke.type) {
                        JokeType.single -> {
                            jokeTextView.visibility = View.VISIBLE
                            twolineJokeLayout.visibility = View.GONE
                            jokeTextView.text = joke.joke.orEmpty()
                        }

                        JokeType.twopart -> {
                            jokeTextView.visibility = View.GONE
                            twolineJokeLayout.visibility = View.VISIBLE
                            jokeSetupTextView.text = joke.setup.orEmpty()
                            jokeDeliveryTextView.text = joke.delivery.orEmpty()
                        }

                        else -> {/* no-op */
                        }
                    }
                    progressBar.visibility = View.GONE
                    newJokeButton.isEnabled = true
                }


                override fun onFailure(t: Throwable?) {
                    Log.e("TEST", "ERROR GETTING HOHMA", t)
                    progressBar.visibility = View.GONE
                    newJokeButton.isEnabled = true
                }

            })
        }
    }

    fun saveJoke(joke: Joke) {
        saveJokeUseCase.saveJoke(joke)
    }
}
