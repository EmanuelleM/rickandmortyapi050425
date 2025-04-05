package com.example.common.network

object ResultMapper {
    fun <X> toResultViewState(responseResult: ResponseResult<X>): ResultViewState<X> {
        return when (responseResult) {
            is ResponseResult.Success -> ResultViewState.Success(responseResult.data)
            is ResponseResult.Error -> ResultViewState.Error(responseResult.resultError)
        }
    }
}
