package ru.ikyzmin.jokes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.JokeType

class JokeDetailsFragment : Fragment(R.layout.fragment_details) {

    val args = navArgs<JokeDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jokeTextView = view.findViewById<TextView>(R.id.joke_text)
        val joke = args.value.joke
        if (joke == null) return
        when (joke!!.type) {
            JokeType.single -> jokeTextView.text = joke!!.joke
            JokeType.twopart -> jokeTextView.text = "${joke!!.setup} \n ${joke!!.delivery}"
            JokeType.unknown -> {/* no-op */
            }
        }

    }
}
