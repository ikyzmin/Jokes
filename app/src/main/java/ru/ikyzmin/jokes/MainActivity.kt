package ru.ikyzmin.jokes

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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private val component = ComponentHolder.getComponent()
    private val jokesRepository: JokesRepository = component.jokesRepository

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
            jokesRepository.getJoke(
                "",
                callback = object : Callback<JokeResponse> {
                    override fun onResponse(
                        call: Call<JokeResponse?>?,
                        response: Response<JokeResponse?>?
                    ) {
                        val joke = response?.body()

                        if (joke != null) {
                            saveJoke(joke)
                            getJokes()
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
                            }
                        }
                        progressBar.visibility = View.GONE
                        newJokeButton.isEnabled = true
                    }

                    override fun onFailure(
                        call: Call<JokeResponse?>?,
                        t: Throwable?
                    ) {
                        Log.e("TEST", "ERROR GETTING HOHMA", t)
                        progressBar.visibility = View.GONE
                        newJokeButton.isEnabled = true
                    }
                })
        }
    }

    fun saveJoke(joke: JokeResponse) {
        coroutineScope.launch {
            component.jokeLocalRepository.insertJoke(joke.toJokeEntity())
        }
    }

    fun getJokes() {
        coroutineScope.launch {
            val jokes = component.jokeLocalRepository.jokes()
            Log.d("JokesDb", "Jokes: $jokes")
        }
    }

    fun JokeResponse.toJokeEntity(): JokeEntity {
        return JokeEntity(
            id = id,
            joke = joke.orEmpty(),
            setup = setup.orEmpty(),
            delivery = delivery.orEmpty(),
            date = LocalDateTime.now(),
        )
    }
}
