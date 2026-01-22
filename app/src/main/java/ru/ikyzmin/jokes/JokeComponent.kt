package ru.ikyzmin.jokes

import android.content.Context
import androidx.room.Room
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class JokeComponent(context: Context) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=utf-8".toMediaType()
            )
        )
        .build()

    val jokesService: JokesService = retrofit.create(JokesService::class.java)

    val jokesRepository = JokesRepository(jokesService)

    lateinit var context: Context

    val db = Room.databaseBuilder(
        context,
        JokesDatabase::class.java, "jokes-database"
    ).build()

    val jokeDao = db.jokeDao()

    val jokeLocalRepository = JokeLocalRepository(jokeDao)
}
