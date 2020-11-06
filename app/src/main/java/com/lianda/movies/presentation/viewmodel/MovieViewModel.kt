package com.lianda.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lianda.movies.domain.model.EndlessMovie
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.domain.repository.MovieRepository
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.utils.common.ResultState
import kotlinx.coroutines.launch

class MovieViewModel (private val repository: MovieRepository): ViewModel(), MovieUseCase {

    private val fetchMovies = MutableLiveData<ResultState<EndlessMovie>>()
    private val fetchMovieDetail = MutableLiveData<ResultState<Movie>>()
    private val fetchVideoTrailer = MutableLiveData<ResultState<Video>>()
    private val fetchReviews = MutableLiveData<ResultState<List<Review>>>()
    
    init {
        fetchMovies.value = ResultState.Loading()
        fetchMovieDetail.value = ResultState.Loading()
        fetchVideoTrailer.value = ResultState.Loading()
        fetchReviews.value = ResultState.Loading()
    }

    override fun fetchMovies(page:Int): LiveData<ResultState<EndlessMovie>>{
        viewModelScope.launch {
            val movieResponse = repository.fetchMovies(page)
            fetchMovies.value = movieResponse
        }
        return fetchMovies
    }

    override fun fetchMovieDetail(movieId:Int): LiveData<ResultState<Movie>>{
        viewModelScope.launch {
            val movieDetailResponse = repository.fetchMovieDetail(movieId)
            fetchMovieDetail.value = movieDetailResponse
        }
        return fetchMovieDetail
    }

    override fun fetchVideoTrailer(movieId:Int): LiveData<ResultState<Video>>{
        viewModelScope.launch {
            val videoTrailerResponse = repository.fetchVideoTrailer(movieId)
            fetchVideoTrailer.value = videoTrailerResponse
        }
        return fetchVideoTrailer
    }

    override fun fetchReviews(movieId:Int): LiveData<ResultState<List<Review>>>{
        viewModelScope.launch {
            val reviewsResponse = repository.fetchReviews(movieId)
            fetchReviews.value = reviewsResponse
        }
        return fetchReviews
    }
}