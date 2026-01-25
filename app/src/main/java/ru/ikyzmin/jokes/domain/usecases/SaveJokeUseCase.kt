package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.Joke


interface SaveJokeUseCase {
    fun saveJoke(joke: Joke)
}
