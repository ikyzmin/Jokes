package ru.ikyzmin.jokes.data.network

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import ru.ikyzmin.jokes.data.network.JokeCategory
import ru.ikyzmin.jokes.domain.models.JokeType

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
data class JokeResponse(
    @SerialName("error")
    val hasError: Boolean,
    val category: JokeCategory,
    val type: JokeType,
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val id: Int,
)
