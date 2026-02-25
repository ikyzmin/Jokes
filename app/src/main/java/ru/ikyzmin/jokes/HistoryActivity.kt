package ru.ikyzmin.jokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class HistoryActivity : AppCompatActivity(R.layout.activity_hystory) {
    private val component = ComponentHolder.getComponent()

    private val jokesDao = component.jokeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        
        loadJokesFromDatabase(recycler)
    }
    
    private fun loadJokesFromDatabase(recycler: RecyclerView) {
        thread {
            val jokes = jokesDao.getJokes()
            runOnUiThread {
                recycler.adapter = JokeHystoryAdapter(jokes)
            }
        }
    }
}
