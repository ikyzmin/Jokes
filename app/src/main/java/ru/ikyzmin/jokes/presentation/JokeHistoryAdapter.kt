package ru.ikyzmin.jokes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.domain.models.Joke

class JokeHistoryAdapter(
    private val jokesData: List<Joke>,
    private val onJokeClicked: (Joke) -> Unit
) :
    RecyclerView.Adapter<JokeHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.jokeText.text = jokesData[position].joke.orEmpty().ifEmpty {
            "${jokesData[position].setup}\n${jokesData[position].delivery}"
        }
        holder.jokeDate.text = jokesData[position].date.toString()
        holder.onClick(jokesData[position], onJokeClicked)
    }

    override fun getItemCount(): Int = jokesData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jokeText: TextView = itemView.findViewById(R.id.joke_text)
        val jokeDate: TextView = itemView.findViewById(R.id.joke_date)
        val jokeContainer: View = itemView.findViewById(R.id.joke_item)

        fun onClick(joke: Joke, onJokeClicked: (Joke) -> Unit) {
            jokeContainer.setOnClickListener {
                onJokeClicked(joke)
            }
        }
    }
}
