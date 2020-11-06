package com.lianda.movies.domain.usecase.contract

import androidx.lifecycle.LiveData
import com.lianda.movies.domain.model.EndlessMovie
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.utils.common.ResultState

interface MovieUseCase {

    fun fetchMovies(page: Int): LiveData<ResultState<EndlessMovie>>
    fun fetchMovieDetail(movieId: Int): LiveData<ResultState<Movie>>
    fun fetchVideoTrailer(movieId: Int): LiveData<ResultState<Video>>
    fun fetchReviews(movieId: Int):LiveData<ResultState<List<Review>>>

}