package com.lianda.movies.domain.usecase.contract

import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.utils.common.ResultState

interface MovieUseCase {

    suspend fun fetchMovies(): ResultState<List<Movie>>
    suspend fun fetchMovieDetail(movieId:Int) : ResultState<Movie>
    suspend fun fetchVideoTrailer(movieId: Int) : ResultState<Video>
    suspend fun fetchReviews(movieId: Int) : ResultState<List<Review>>

}