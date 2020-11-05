package com.lianda.movies.utils.common

sealed class ResultState<out T> {

    data class Loading<out T>(val data: T?=null) : ResultState<T>()

    data class Empty<out T>(val data: T?=null) : ResultState<T>()

    data class Success<out T>(val data: T,val message:String) : ResultState<T>()

    data class Error(val throwable: Throwable) : ResultState<Nothing>()


}