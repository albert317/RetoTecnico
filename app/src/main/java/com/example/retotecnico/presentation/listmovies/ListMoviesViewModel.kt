package com.example.retotecnico.presentation.listmovies

import androidx.lifecycle.*
import com.example.retotecnico.domain.model.Failure
import com.example.retotecnico.domain.model.Movie
import com.example.retotecnico.domain.model.ResultType
import com.example.retotecnico.domain.usecase.listmovies.GetMoviesUseCase
import kotlinx.coroutines.launch

class ListMoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    fun refresh(page:Int=1) {
        _state.value = UiState(loading = true)
        viewModelScope.launch {
            when (val result = getMoviesUseCase(page)) {
                is ResultType.Success -> {
                    _state.value = UiState(loading = false, result.value)
                }
                is ResultType.Error -> {
                    _state.value = UiState(loading = false)
                }
            }
        }
    }

    fun onPlaceClicked(movie: Movie) {
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null,
        val error: Failure? = null
    )
}

@Suppress("UNCHECKED_CAST")
class ListMoviesViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListMoviesViewModel(getMoviesUseCase) as T
    }
}