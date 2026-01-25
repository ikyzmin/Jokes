package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.repositories.JokeRepository

class SaveJokeUseCaseImpl(private val jokeRepository: JokeRepository) : SaveJokeUseCase {
    override fun saveJoke(joke: Joke) {
        jokeRepository.saveJoke(joke)
    }
}
