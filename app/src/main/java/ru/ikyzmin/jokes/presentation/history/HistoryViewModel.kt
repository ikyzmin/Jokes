package ru.ikyzmin.jokes.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.ListCallback
import ru.ikyzmin.jokes.domain.usecases.GetJokeListUseCase

class HistoryViewModel(val getJokesListUseCase: GetJokeListUseCase) : ViewModel() {
    private val _viewState = MutableLiveData<HistoryState>()
    val viewState: LiveData<HistoryState> = _viewState

    fun loadHistory() {
        getJokesListUseCase.getJokesList(object : ListCallback {
            override fun onSuccess(jokes: List<Joke>) {
                _viewState.value = HistoryState.Content(jokes)
            }

            override fun onFailure(t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }
}
