package com.example.retotecnico.domain.usecase.listmovies

import com.example.retotecnico.data.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(page:Int) = repository.requestMovies(page)

}