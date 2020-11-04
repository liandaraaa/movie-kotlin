package com.lianda.movies.utils.extentions

import com.lianda.movies.utils.common.ApiErrorOperator
import com.lianda.movies.utils.common.ResultState
import retrofit2.Response

fun <T: Any> handleApiSuccess(message: String = "",  data: T) : ResultState.Success<T>{
    return ResultState.Success(data, message)
}

fun <T : Any> handleApiError(response:Response<T>): ResultState.Error {
    val error = ApiErrorOperator.parseError(response)
    if(error.statusMessage.isEmpty()){
        return ResultState.Error(Exception(error.statusMessage))
    }
    return ResultState.Error(Throwable(error.statusMessage))
}

