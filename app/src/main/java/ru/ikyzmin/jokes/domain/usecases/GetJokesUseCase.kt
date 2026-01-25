package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.Callback


interface GetJokesUseCase {
    fun getJokes(blacklist: String, callback: Callback)
}
