package com.lianda.movies.domain.repository

import com.lianda.movies.domain.entities.City
import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.utils.common.ResultState

interface MovieRepository {

    suspend fun fetchMovies() : ResultState<List<Movie>>

}