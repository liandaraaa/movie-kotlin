package com.lianda.movies.domain.entities

data class Movie(
    val backdropPath: String,
    val genres: List<String>,
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double
)