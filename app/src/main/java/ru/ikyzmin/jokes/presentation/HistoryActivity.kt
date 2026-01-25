package ru.ikyzmin.jokes.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.ListCallback

class HistoryActivity : AppCompatActivity(R.layout.activity_hystory) {
    private val component = ComponentHolder.getComponent()

    private val coroutineScope = this.lifecycleScope
    private val getJokesUseCase = component.getJokesHistoryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        coroutineScope.launch {
            getJokesUseCase.getJokesList(object : ListCallback {
                override fun onSuccess(jokes: List<Joke>) {
                    recycler.adapter = JokeHistoryAdapter(jokes, onJokeClicked = {
                        startActivity(Intent(this@HistoryActivity, JokeDetailsActivity::class.java))
                    })
                }

                override fun onFailure(t: Throwable?) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}
