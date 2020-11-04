package com.lianda.movies.domain.usecase.implementation

import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.domain.repository.MovieRepository
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.utils.common.ResultState

class MovieUseCaseImpl (private val repository: MovieRepository):MovieUseCase{
    override suspend fun fetchMovies(): ResultState<List<Movie>> {
        return repository.fetchMovies()
    }

}