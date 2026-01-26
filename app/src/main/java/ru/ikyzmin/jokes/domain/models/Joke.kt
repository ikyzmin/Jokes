package ru.ikyzmin.jokes.domain.models

import android.os.Parcelable
import java.time.LocalDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
data class Joke(
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val id: Int,
    val date: LocalDateTime,
    val type: JokeType,
) : Parcelable
