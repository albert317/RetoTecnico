package com.example.retotecnico.presentation.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retotecnico.domain.model.Movie
import kotlinx.coroutines.launch

class ListMoviesViewModel : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    fun refresh() {
        viewModelScope.launch {
            //_state.value = UiState(loading = true)
            _state.value = UiState(
                loading = false,
                movies = listOf(
                    Movie(
                        true,
                        "val backdrop_path: String?",
                        listOf(1, 2, 3),
                        1,
                        "sd",
                        "ds",
                        "overview",
                        2.0,
                        "/v28T5F1IygM8vXWZIycfNEm3xcL.jpg",
                        "release_date",
                        "title",
                        true,
                        vote_average = 2.0,
                        3
                    ), Movie(
                        true,
                        "val backdrop_path: String?",
                        listOf(1, 2, 3),
                        1,
                        "sd",
                        "ds",
                        "overview",
                        2.0,
                        "/v28T5F1IygM8vXWZIycfNEm3xcL.jpg",
                        "release_date",
                        "title",
                        true,
                        vote_average = 2.0,
                        3
                    )
                )
            )
        }
    }

    fun onPlaceClicked(movie: Movie) {
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null
    )
}