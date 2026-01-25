package ru.ikyzmin.jokes.domain.models

import java.time.LocalDateTime

data class Joke(
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val id: Int,
    val date: LocalDateTime,
    val type: JokeType,
)
