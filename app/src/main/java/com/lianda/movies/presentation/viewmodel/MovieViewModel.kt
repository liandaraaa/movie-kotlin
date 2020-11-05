package com.lianda.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.utils.common.ResultState
import kotlinx.coroutines.launch

class MovieViewModel (private val useCase: MovieUseCase): ViewModel() {

    private val fetchMovies = MutableLiveData<ResultState<List<Movie>>>()
    private val fetchMovieDetail = MutableLiveData<ResultState<Movie>>()
    private val fetchVideoTrailer = MutableLiveData<ResultState<Video>>()
    private val fetchReviews = MutableLiveData<ResultState<List<Review>>>()
    
    init {
        fetchMovies.value = ResultState.Loading()
        fetchMovieDetail.value = ResultState.Loading()
        fetchVideoTrailer.value = ResultState.Loading()
        fetchReviews.value = ResultState.Loading()
    }

    fun fetchMovies(): LiveData<ResultState<List<Movie>>>{
        viewModelScope.launch {
            val movieResponse = useCase.fetchMovies()
            fetchMovies.value = movieResponse
        }
        return fetchMovies
    }

    fun fetchMovieDetail(movieId:Int): LiveData<ResultState<Movie>>{
        viewModelScope.launch {
            val movieDetailResponse = useCase.fetchMovieDetail(movieId)
            fetchMovieDetail.value = movieDetailResponse
        }
        return fetchMovieDetail
    }

    fun fetchVideoTrailer(movieId:Int): LiveData<ResultState<Video>>{
        viewModelScope.launch {
            val videoTrailerResponse = useCase.fetchVideoTrailer(movieId)
            fetchVideoTrailer.value = videoTrailerResponse
        }
        return fetchVideoTrailer
    }

    fun fetchReviews(movieId:Int): LiveData<ResultState<List<Review>>>{
        viewModelScope.launch {
            val reviewsResponse = useCase.fetchReviews(movieId)
            fetchReviews.value = reviewsResponse
        }
        return fetchReviews
    }
}