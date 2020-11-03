package com.lianda.movies.base

import com.google.gson.annotations.SerializedName

data class BaseDataSourceApi<T>(
    @SerializedName("code")
    val statusCode:Int?,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val data: T? = null
)