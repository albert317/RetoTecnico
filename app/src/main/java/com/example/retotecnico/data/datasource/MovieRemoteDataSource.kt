package com.example.retotecnico.data.datasource

import com.example.retotecnico.data.server.RemoteConnection


class MovieRemoteDataSource(private val apiKey: String) {
    suspend fun findMovies() = RemoteConnection.service.listMovies(apiKey)
}