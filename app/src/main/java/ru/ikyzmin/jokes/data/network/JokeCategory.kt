package ru.ikyzmin.jokes.data.network

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
