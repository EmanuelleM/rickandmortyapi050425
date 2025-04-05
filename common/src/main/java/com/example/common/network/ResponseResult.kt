package com.example.common.network

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()

    data class Error(val resultError: String) : ResponseResult<Nothing>()
}
