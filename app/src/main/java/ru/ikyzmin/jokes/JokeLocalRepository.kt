package ru.ikyzmin.jokes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeLocalRepository(private val dao: JokesDao) {

    suspend fun jokes(): List<JokeEntity> = dao.getJokes()

    suspend fun insertJoke(joke: JokeEntity) = dao.addJoke(joke)

}
