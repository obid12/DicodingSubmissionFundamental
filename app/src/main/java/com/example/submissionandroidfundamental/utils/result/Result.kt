package com.example.submissionandroidfundamental.utils.result

sealed class Result<out T : Any, out U : Any> {
    data class Success<T : Any>(val data: T) : Result<T, Nothing>()
    data class Error<T : Any, U : Any>(val data: T? = null, val response: U) : Result<T, U>()
}
