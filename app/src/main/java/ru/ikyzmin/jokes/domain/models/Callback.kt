package ru.ikyzmin.jokes.domain.models

interface Callback {
    fun onSuccess(joke: Joke)

    fun onFailure(t: Throwable?)
}

