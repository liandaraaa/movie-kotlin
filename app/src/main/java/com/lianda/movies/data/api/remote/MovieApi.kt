package com.lianda.movies.data.api.remote

import com.lianda.movies.data.api.entities.MovieListSourceApi
import com.lianda.movies.data.api.entities.MovieSourceApi
import com.lianda.movies.data.api.entities.ReviewListSourceApi
import com.lianda.movies.data.api.entities.VideoListSourceApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("discover/movie")
    suspend fun fetchMovies() : Response<MovieListSourceApi>

    @GET("movie/{id}")
    suspend fun fetchMovieDetail(@Path("id") movieId:Int) : Response<MovieSourceApi>

    @GET("movie/{id}/videos")
    suspend fun fetchVideos(@Path("id") movieId:Int) : Response<VideoListSourceApi>

    @GET("movie/{id}/reviews")
    suspend fun fetchReviews(@Path("id") movieId:Int) : Response<ReviewListSourceApi>

}