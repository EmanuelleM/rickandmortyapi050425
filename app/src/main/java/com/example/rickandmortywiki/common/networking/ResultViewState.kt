package com.example.rickandmortywiki.common.networking

sealed class ResultViewState<out R> {

    object Loading : ResultViewState<Nothing>()
    object Initial : ResultViewState<Nothing>()

    data class Success<out T>(val data: T) : ResultViewState<T>()

    data class Error(val resultError: String) : ResultViewState<Nothing>()
}