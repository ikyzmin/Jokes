package ru.ikyzmin.jokes.domain.repositories

import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.ListCallback

interface JokeRepository {
    fun getJoke(callback: Callback)
    fun saveJoke(joke: Joke)
    fun getJokes(callback: ListCallback)
}
