package com.lianda.movies.data.api.entities


import com.google.gson.annotations.SerializedName
import com.lianda.movies.BuildConfig
import com.lianda.movies.domain.entities.Movie

data class MovieSourceApi(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genres")
    val genreSourceApis: List<GenreSourceApi>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?
){

    fun toMovie():Movie{
        return Movie(
            backdropPath = getImage(backdropPath.orEmpty()),
            genres = genreSourceApis?.map { it.name }.orEmpty(),
            id = id ?: 0,
            overview = overview.orEmpty(),
            posterPath = getImage(posterPath.orEmpty()),
            popularity = popularity ?: 0.0,
            originalLanguage = originalLanguage.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            runtime = runtime ?: 0,
            status = status.orEmpty(),
            tagline = tagline.orEmpty(),
            title = title.orEmpty(),
            video = video ?: false,
            voteAverage = voteAverage ?: 0.0
        )
    }

    fun getImage(fileName:String) = BuildConfig.SERVER_IMAGE_URL+fileName

    fun getVideo(fileName:String) = BuildConfig.SERVER_YOUTUBE_URL+fileName
}