package com.lianda.movies.domain.usecase.contract

import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.utils.common.ResultState

interface MovieUseCase {

    suspend fun fetchMovies(): ResultState<List<Movie>>
}