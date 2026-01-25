package ru.ikyzmin.jokes.data.network

import kotlinx.serialization.Serializable

@Serializable
enum class BlackListFlags {
    nsfw,
    religious,
    political,
    racist,
    sexist,
    explicit
}
