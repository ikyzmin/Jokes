package ru.ikyzmin.jokes.presentation.history

import ru.ikyzmin.jokes.domain.models.Joke

interface HistoryState {
    data class Content(val jokes: List<Joke>) : HistoryState
}
