package com.example.retotecnico.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("https://api.themoviedb.org/3/movie/upcoming?page=1")
    suspend fun listMovies(@Query("api_key") apiKey:String): RemoteResult
}