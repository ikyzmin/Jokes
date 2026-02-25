package ru.ikyzmin.jokes

import kotlinx.serialization.Serializable

@Serializable
enum class JokeType {
    single,
    twopart,
    unknown,
}
