package com.lianda.movies.domain.model

import android.os.Parcelable
import com.lianda.movies.utils.extentions.emptyString
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String = emptyString(),
    val genres: String = emptyString(),
    val id: Int = 0,
    val overview: String = emptyString(),
    val originalLanguage: String = emptyString(),
    val popularity: Double = 0.0,
    val posterPath: String = emptyString(),
    val releaseDate: String = emptyString(),
    val runtime: Int = 0,
    val status: String = emptyString(),
    val tagline: String = emptyString(),
    val title: String = emptyString(),
    val video: Boolean = false,
    val voteAverage: Double = 0.0
) : Parcelable