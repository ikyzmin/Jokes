package ru.ikyzmin.jokes.presentation.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.presentation.ComponentHolder
import ru.ikyzmin.jokes.presentation.JokeDetailsActivity

class HistoryActivity : AppCompatActivity(R.layout.activity_hystory) {
    private val component = ComponentHolder.getComponent()

    private val historyViewModel: HistoryViewModel by viewModels { component.historyViewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        historyViewModel.loadHistory()
        historyViewModel.viewState.observe(this) { state ->
            when (state) {
                is HistoryState.Content -> {
                    recycler.adapter = JokeHistoryAdapter(state.jokes, onJokeClicked = {
                        startActivity(Intent(this, JokeDetailsActivity::class.java))
                    })
                }
            }
        }
    }
}
