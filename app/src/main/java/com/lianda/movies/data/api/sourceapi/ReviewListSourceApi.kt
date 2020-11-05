package com.lianda.movies.data.api.sourceapi


import com.google.gson.annotations.SerializedName

data class ReviewListSourceApi(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val reviewSourceApis: List<ReviewSourceApi>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)