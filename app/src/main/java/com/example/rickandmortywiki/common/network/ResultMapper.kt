package com.example.rickandmortywiki.common.network

object ResultMapper {
    fun <T> toResultViewState(responseResult: ResponseResult<T>): ResultViewState<T> {
        return when (responseResult) {
            is ResponseResult.Success -> ResultViewState.Success(responseResult.data)
            is ResponseResult.Error -> ResultViewState.Error(responseResult.resultError)
        }
    }
}
