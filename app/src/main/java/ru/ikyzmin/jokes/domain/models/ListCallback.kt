package ru.ikyzmin.jokes.domain.models

interface ListCallback {
    fun onSuccess(jokes: List<Joke>)

    fun onFailure(t: Throwable?)
}
