package com.lianda.movies.utils.di

import com.lianda.movies.data.api.remote.CityApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideCityApi(get()) }
}

fun provideCityApi(retrofit: Retrofit):CityApi = retrofit.create(CityApi::class.java)