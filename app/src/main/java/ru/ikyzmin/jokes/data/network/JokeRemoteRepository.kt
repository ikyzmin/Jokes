package ru.ikyzmin.jokes.data.network

import retrofit2.Call
import retrofit2.Response
import ru.ikyzmin.jokes.data.network.extenstion.toDomain
import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.ListCallback
import retrofit2.Callback as RetrofitCallback
import ru.ikyzmin.jokes.domain.repositories.JokeRepository

class JokeRemoteRepository(private val jokesService: JokesService) : JokeRepository {
    override fun getJoke(callback: Callback) {
        jokesService.jokes("").enqueue(object : RetrofitCallback<JokeResponse> {
            override fun onResponse(
                call: Call<JokeResponse?>?,
                response: Response<JokeResponse?>?
            ) {
                if (response == null)
                    return
                if (response.body() == null)
                    return

                callback.onSuccess(response.body()!!.toDomain())
            }

            override fun onFailure(
                call: Call<JokeResponse?>?,
                t: Throwable?
            ) {
                callback.onFailure(t)
            }
        })
    }

    override fun saveJoke(joke: Joke) {
        TODO("Not yet implemented")
    }

    override fun getJokes(callback: ListCallback) {
        TODO("Not yet implemented")
    }
}
