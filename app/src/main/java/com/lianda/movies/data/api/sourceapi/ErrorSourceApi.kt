package com.lianda.movies.data.api.sourceapi


import com.google.gson.annotations.SerializedName

data class ErrorSourceApi(
    @SerializedName("status_code")
    val statusCode: Int = 0,
    @SerializedName("status_message")
    val statusMessage: String = "",
    @SerializedName("success")
    val success: Boolean = false
)