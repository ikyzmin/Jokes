package ru.ikyzmin.jokes.presentation.mainjoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ikyzmin.jokes.domain.models.Callback
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.usecases.GetJokesUseCase
import ru.ikyzmin.jokes.domain.usecases.SaveJokeUseCase

class MainViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val saveJokeUseCase: SaveJokeUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<MainState>()
    val viewState: LiveData<MainState> = _viewState

    fun loadJoke() {
        _viewState.value = MainState.Loading
        getJokesUseCase.getJokes("", object : Callback {
            override fun onSuccess(joke: Joke) {
                saveJokeUseCase.saveJoke(joke)
                _viewState.value = MainState.Content(joke)
            }

            override fun onFailure(t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
}
