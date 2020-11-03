package com.lianda.movies.utils.extentions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.lianda.movies.utils.common.ApiErrorOperator
import com.lianda.movies.utils.common.ResultState
import okhttp3.ResponseBody
import retrofit2.HttpException

fun hasNetwork(context: Context): Boolean {
    var result = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as
            ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    if (this.isConnected){
                        result = true

                    }
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    if (this.isConnected){
                        result = true

                    }
                }
            }
        }
    }
    return result
}

fun <T: Any> handleApiSuccess(message: String,  data: T) : ResultState.Success<T>{
    return ResultState.Success(data, message)
}

fun  <T : Any> handleApiError(exception: java.lang.Exception): ResultState.Error<T> {
   return when(exception){
        is HttpException -> {
            if (exception.code() == 504){
                ResultState.Error(throwable = Throwable("Tidak ada koneksi internet. Silahkan coba lagi"))
            }else{
                ResultState.Error(throwable = exception)
            }
        }
       else -> {
           ResultState.Error(throwable = exception)
       }
    }
}

fun <T : Any> handleApiError(errorBody: ResponseBody?): ResultState.Error<T> {
    val error = ApiErrorOperator.parseError(errorBody)
    if(error.code.isEmpty()){
        return ResultState.Error(Throwable(error.error))
    }
    return ResultState.Error(Throwable(error.message))
}

