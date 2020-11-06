package com.lianda.movies.data.api.interceptor

import com.lianda.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor:Interceptor{

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = mapHeaders(chain)
        return chain.proceed(request)
    }

    private fun mapHeaders(chain: Interceptor.Chain):Request{
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization",BuildConfig.ACCESS_TOKEN)
            .addHeader("Content-Type","application/json;charset=utf-8")
        return requestBuilder.build()
    }


}