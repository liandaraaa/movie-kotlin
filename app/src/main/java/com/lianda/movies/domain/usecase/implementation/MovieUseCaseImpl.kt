package com.lianda.movies.domain.usecase.implementation

import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.domain.repository.MovieRepository
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.utils.common.ResultState

class MovieUseCaseImpl (private val repository: MovieRepository):MovieUseCase{
    override suspend fun fetchMovies(): ResultState<List<Movie>> {
        return repository.fetchMovies()
    }

    override suspend fun fetchMovieDetail(movieId: Int): ResultState<Movie> {
        return repository.fetchMovieDetail(movieId)
    }

    override suspend fun fetchVideoTrailer(movieId: Int): ResultState<Video> {
        return repository.fetchVideoTrailer(movieId)
    }

    override suspend fun fetchReviews(movieId: Int): ResultState<List<Review>> {
        return repository.fetchReviews(movieId)
    }

}