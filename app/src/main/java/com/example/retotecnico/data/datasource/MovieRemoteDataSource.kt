package com.example.retotecnico.data.datasource

import com.example.retotecnico.data.server.RemoteConnection


class MovieRemoteDataSource(private val apiKey: String) {
    suspend fun findMovies(page: Int) = RemoteConnection.service.listMovies(apiKey, page)
}