package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.repositories.JokeRepository

class GetJokesUseCaseImpl(private val repository: JokeRepository) : GetJokesUseCase {
    override fun getJokes(blacklist: String, callback: Callback) {
        repository.getJoke(callback)
        //что то ещё
    }
}
