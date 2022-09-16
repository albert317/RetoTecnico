package com.example.retotecnico.data

import android.app.Application
import com.example.retotecnico.R
import com.example.retotecnico.data.datasource.MovieRemoteDataSource
import com.example.retotecnico.data.server.RemoteMovie
import com.example.retotecnico.domain.model.Failure
import com.example.retotecnico.domain.model.Movie
import com.example.retotecnico.domain.model.ResultType

class MoviesRepository(application: Application) {
    private val remoteDataSource =
        MovieRemoteDataSource(application.getString(R.string.api_key))

    suspend fun requestMovies(page:Int): ResultType<List<Movie>, Failure> {
        return try {
            val de = remoteDataSource.findMovies(page)
            ResultType.Success(de.results.toLocalModel())
        } catch (e: Exception) {
            ResultType.Error(Failure.ErrorMessage(e.message))
        }
    }
}

private fun List<RemoteMovie>.toLocalModel(): List<Movie> = map { it.toLocalModel() }

private fun RemoteMovie.toLocalModel(): Movie = Movie(
    adult,
    backdrop_path,
    genre_ids,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)