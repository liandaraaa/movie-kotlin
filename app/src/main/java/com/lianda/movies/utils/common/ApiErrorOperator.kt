package com.lianda.movies.utils.common

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import java.io.IOException

object ApiErrorOperator {

    fun parseError(errorBody:ResponseBody?): ApiError {
        val gson = GsonBuilder().create()
        val error: ApiError

        try {
            error = gson.fromJson(errorBody?.string(), ApiError::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d("error", it) }
            return ApiError()
        }
        return error
    }

}

data class ApiError(
    val code: String = "",
    val message: String = "",
    val status: String = "",
    val error: String = ""
)