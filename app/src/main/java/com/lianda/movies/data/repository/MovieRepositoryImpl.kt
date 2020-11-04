package com.lianda.movies.data.repository

import com.lianda.movies.data.api.remote.MovieApi
import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.domain.repository.MovieRepository
import com.lianda.movies.utils.common.ResultState
import com.lianda.movies.utils.extentions.handleApiError
import com.lianda.movies.utils.extentions.handleApiSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl (private val api: MovieApi): MovieRepository {

    override suspend fun fetchMovies(): ResultState<List<Movie>> {

        return try {
            val response = api.fetchMovies()
            if (response.isSuccessful){
                response.body()?.let {
                    withContext(Dispatchers.IO){
                        handleApiSuccess(data = it.movieSourceApis?.map { it.toMovie() }.orEmpty())
                    }
                } ?: handleApiError(response)
            }else{
                handleApiError(response)
            }
        }catch (e: Exception){
            ResultState.Error(e)
        }
    }

}