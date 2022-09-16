package com.example.retotecnico.domain.model

sealed class Failure {
    object NetworkConnection : Failure()
    object UnExcepted : Failure()
    data class ErrorMessage(val message: String?) : Failure()
}