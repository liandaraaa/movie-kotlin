package com.lianda.movies.domain.repository

import com.lianda.movies.domain.model.EndlessMovie
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.utils.common.ResultState

interface MovieRepository {

    suspend fun fetchMovies(page:Int) : ResultState<EndlessMovie>
    suspend fun fetchMovieDetail(movieId:Int) : ResultState<Movie>
    suspend fun fetchVideoTrailer(movieId: Int) : ResultState<Video>
    suspend fun fetchReviews(movieId: Int) : ResultState<List<Review>>

}