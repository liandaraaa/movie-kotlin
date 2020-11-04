package com.lianda.movies.data.api.remote

import com.lianda.movies.data.api.entities.MovieListSourceApi
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("discover/movie")
    suspend fun fetchMovies() : Response<MovieListSourceApi>


}