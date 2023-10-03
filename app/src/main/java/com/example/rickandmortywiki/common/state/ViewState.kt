package com.example.rickandmortywiki.common.state

sealed class ViewState<T>(
    val value: T? = null,
    val message: String? = null,
) {
    data class Success<T>(val data: T) : ViewState<T>(data)

    class Error<T>(message: String?, data: T? = null) : ViewState<T>(data, message)
    object Loading : ViewState<Nothing>()
}
