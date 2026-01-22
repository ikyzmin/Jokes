package ru.ikyzmin.jokes

import retrofit2.Callback


class JokesRepository(private val jokesService: JokesService) {
    fun getJoke(blacklist: String, callback: Callback<JokeResponse>) {
        jokesService.jokes(blacklist).enqueue(callback)
    }
}
