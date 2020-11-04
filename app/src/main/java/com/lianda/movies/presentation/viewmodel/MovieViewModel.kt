package com.lianda.movies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.utils.common.ResultState
import kotlinx.coroutines.launch

class MovieViewModel (private val useCase: MovieUseCase): ViewModel() {

    fun fetchMovies(): MutableLiveData<ResultState<List<Movie>>>{
        val fetchMovies = MutableLiveData<ResultState<List<Movie>>>()

        viewModelScope.launch {
            val movieResponse = useCase.fetchMovies()
            fetchMovies.value = movieResponse
        }

        return fetchMovies
    }
}