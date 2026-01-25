package ru.ikyzmin.jokes.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesService {
    @GET("joke/Programming")
    fun jokes(@Query("blacklistFlags") blacklist: String): Call<JokeResponse>
}
