package com.lianda.movies.data.api.sourceapi


import com.google.gson.annotations.SerializedName

data class MovieListSourceApi(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movieSourceApis: List<MovieSourceApi>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)