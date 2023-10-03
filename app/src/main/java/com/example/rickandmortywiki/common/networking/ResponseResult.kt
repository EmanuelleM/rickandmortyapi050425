package com.example.rickandmortywiki.common.networking

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()

    data class Error(val resultError: String) : ResponseResult<Nothing>()
}

sealed class ResultViewState<out R> {

    object Loading : ResultViewState<Nothing>()
    object Initial : ResultViewState<Nothing>()

    data class Success<out T>(val data: T) : ResultViewState<T>()

    data class Error(val resultError: String) : ResultViewState<Nothing>()
}

object ResultMapper {
    fun <T> toResultViewState(responseResult: ResponseResult<T>): ResultViewState<T> {
        return when (responseResult) {
            is ResponseResult.Success -> ResultViewState.Success(responseResult.data)
            is ResponseResult.Error -> ResultViewState.Error(responseResult.resultError)
        }
    }
}
