package com.lianda.movies.utils.di

import com.lianda.movies.data.api.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideMovieApi(get()) }
}

fun provideMovieApi(retrofit: Retrofit):MovieApi = retrofit.create(MovieApi::class.java)