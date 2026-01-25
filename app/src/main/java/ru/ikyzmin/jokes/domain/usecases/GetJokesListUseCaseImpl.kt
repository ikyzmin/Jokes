package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.ListCallback
import ru.ikyzmin.jokes.domain.repositories.JokeRepository

class GetJokesListUseCaseImpl(private val repository: JokeRepository) : GetJokeListUseCase {
    override fun getJokesList(callback: ListCallback) {
        repository.getJokes(callback)
    }
}
