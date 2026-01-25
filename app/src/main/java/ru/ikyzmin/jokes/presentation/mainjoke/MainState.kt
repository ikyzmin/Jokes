package ru.ikyzmin.jokes.presentation.mainjoke

import ru.ikyzmin.jokes.domain.models.Joke

sealed interface MainState {
    data class Content(val joke: Joke) : MainState
    data object Loading : MainState
    data object Error : MainState
}
