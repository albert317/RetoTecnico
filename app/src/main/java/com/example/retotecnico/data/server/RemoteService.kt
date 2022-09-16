package com.example.retotecnico.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("https://api.themoviedb.org/3/movie/upcoming")
    suspend fun listMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): RemoteResult
}