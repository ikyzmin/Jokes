package ru.ikyzmin.jokes.data.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.ikyzmin.jokes.data.db.extensions.toDomain
import ru.ikyzmin.jokes.data.db.extensions.toData
import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.ListCallback
import ru.ikyzmin.jokes.domain.repositories.JokeRepository

class JokeLocalRepository(private val jokeDao: JokesDao) : JokeRepository {

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun getJoke(callback: Callback) {
        coroutineScope.launch {
            val joke = jokeDao.getJokes(0)
            withContext(Dispatchers.Main) {
                callback.onSuccess(joke.toDomain())
            }
        }
    }

    override fun saveJoke(joke: Joke) {
        coroutineScope.launch {
            jokeDao.addJoke(joke.toData())
        }
    }

    override fun getJokes(callback: ListCallback) {
        coroutineScope.launch {
            val jokes = jokeDao.getJokes()
            withContext(Dispatchers.Main) {
                callback.onSuccess(jokes.map { joke -> joke.toDomain() })
            }
        }
    }
}
