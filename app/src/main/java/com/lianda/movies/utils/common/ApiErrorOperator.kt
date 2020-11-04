package com.lianda.movies.utils.common

import android.util.Log
import com.google.gson.GsonBuilder
import com.lianda.movies.data.api.entities.ErrorSourceApi
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

object ApiErrorOperator {

    fun parseError(response:Response<*>?): ErrorSourceApi {
        val gson = GsonBuilder().create()
        val error: ErrorSourceApi

        try {
            error = gson.fromJson(response?.errorBody()?.string(), ErrorSourceApi::class.java)
        } catch (e: IOException) {
            e.message?.let { Log.d("error", it) }
            return ErrorSourceApi()
        }
        return error
    }

}

data class ApiError(
    val code: String = "",
    val message: String = "",
    val status: Boolean = false
){
    constructor(): this("","",false)
}