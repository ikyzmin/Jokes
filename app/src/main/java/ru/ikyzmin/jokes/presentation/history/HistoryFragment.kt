package ru.ikyzmin.jokes.presentation.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.presentation.ComponentHolder
import ru.ikyzmin.jokes.presentation.JokeDetailsActivity

class HistoryFragment : Fragment(R.layout.fragment_hystory) {
    private val component = ComponentHolder.getComponent()

    private val historyViewModel: HistoryViewModel by viewModels { component.historyViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        historyViewModel.loadHistory()
        historyViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HistoryState.Content -> {
                    recycler.adapter = JokeHistoryAdapter(state.jokes, onJokeClicked = {
                        startActivity(Intent(requireContext(), JokeDetailsActivity::class.java))
                    })
                }
            }
        }
    }
}
