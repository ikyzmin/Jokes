package ru.ikyzmin.jokes.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.ikyzmin.jokes.data.db.JokeLocalRepository
import ru.ikyzmin.jokes.data.db.JokesDatabase
import ru.ikyzmin.jokes.data.network.JokeRemoteRepository
import ru.ikyzmin.jokes.data.network.JokesService
import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.JokeType
import ru.ikyzmin.jokes.domain.repositories.JokeRepository
import ru.ikyzmin.jokes.domain.usecases.GetJokeListUseCase
import ru.ikyzmin.jokes.domain.usecases.GetJokesListUseCaseImpl
import ru.ikyzmin.jokes.domain.usecases.GetJokesUseCaseImpl
import ru.ikyzmin.jokes.domain.usecases.SaveJokeUseCaseImpl
import ru.ikyzmin.jokes.presentation.history.HistoryViewModel
import ru.ikyzmin.jokes.presentation.mainjoke.MainViewModel
import ru.ikyzmin.jokes.presentation.navigation.NavigationHolderImpl
import ru.ikyzmin.jokes.presentation.navigation.RouterImpl
import java.time.LocalDateTime

class JokeComponent(context: Context) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(
            Json.Default.asConverterFactory(
                "application/json; charset=utf-8".toMediaType()
            )
        )
        .build()

    val jokesService: JokesService = retrofit.create(JokesService::class.java)

    val jokeRemoteRepository = JokeRemoteRepository(jokesService)

    val getJokesUseCase = GetJokesUseCaseImpl(jokeRemoteRepository)

    lateinit var context: Context

    val db = Room.databaseBuilder(
        context,
        JokesDatabase::class.java, "jokes-database"
    ).build()

    val jokeDao = db.jokeDao()

    val jokeLocalRepository = JokeLocalRepository(jokeDao)

    val getJokesHistoryUseCase = GetJokesListUseCaseImpl(jokeLocalRepository)

    val saveJokeUseCase = SaveJokeUseCaseImpl(jokeLocalRepository)

    val mainViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(getJokesUseCase, saveJokeUseCase) as T
        }
    }

    val historyViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HistoryViewModel(getJokesHistoryUseCase) as T
        }
    }

    val navigatorHolder = NavigationHolderImpl()

    val router = RouterImpl(navigatorHolder)
}
