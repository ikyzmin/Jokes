package ru.ikyzmin.jokes

class JokeLocalRepository(private val dao: JokesDao) {

    fun jokes(): List<JokeEntity> = dao.getJokes()

    fun insertJoke(joke: JokeEntity) = dao.addJoke(joke)

}
