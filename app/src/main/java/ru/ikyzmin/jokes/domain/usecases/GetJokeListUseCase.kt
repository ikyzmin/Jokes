package ru.ikyzmin.jokes.domain.usecases

import ru.ikyzmin.jokes.domain.models.ListCallback

interface GetJokeListUseCase {
    fun getJokesList(callback: ListCallback)
}
