package ru.ikyzmin.jokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokeHystoryAdapter(private val jokesData: List<JokeEntity>) :
    RecyclerView.Adapter<JokeHystoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JokeHystoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: JokeHystoryAdapter.ViewHolder, position: Int) {
        holder.jokeText.text = jokesData[position].joke.ifEmpty {
            "${jokesData[position].setup}\n${jokesData[position].delivery}"
        }
        holder.jokeDate.text = jokesData[position].date.toString()
    }

    override fun getItemCount(): Int = jokesData.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jokeText: TextView = itemView.findViewById(R.id.joke_text)
        val jokeDate: TextView = itemView.findViewById(R.id.joke_date)
    }
}
