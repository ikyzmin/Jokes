package ru.ikyzmin.jokes

import kotlinx.serialization.Serializable

@Serializable
enum class JokeCategory {
    Programming,
    Misc,
    Dark,
    Pun,
    Spooky,
    Christmas
}
