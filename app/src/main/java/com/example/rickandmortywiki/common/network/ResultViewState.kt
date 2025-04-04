package com.example.rickandmortywiki.common.network

sealed class ResultViewState<out R> {

    object Loading : ResultViewState<Nothing>()

    data class Success<out T>(val data: T) : ResultViewState<T>()

    data class Error(val resultError: String) : ResultViewState<Nothing>()
}