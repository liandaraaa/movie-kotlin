package com.lianda.movies.utils.common

sealed class ResultState<T> {

    data class Loading<T>(val data: T?=null) : ResultState<T>()

    data class Success<T>(val data: T,val message:String) : ResultState<T>()

    data class Failed<T>(val data: T?=null, val code:Int,val message:String) : ResultState<T>()

    data class Error<T>(val throwable: Throwable, val data: T?=null) : ResultState<T>()


}