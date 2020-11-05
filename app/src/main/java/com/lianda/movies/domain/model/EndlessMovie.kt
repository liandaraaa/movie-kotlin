package com.lianda.movies.domain.model

data class EndlessMovie(
    val totalPage:Int = 0,
    val movies:List<Movie> = listOf()
)