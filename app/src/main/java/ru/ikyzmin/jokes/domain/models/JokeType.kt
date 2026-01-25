package ru.ikyzmin.jokes.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class JokeType {
    single,
    twopart,
    unknown,
}
