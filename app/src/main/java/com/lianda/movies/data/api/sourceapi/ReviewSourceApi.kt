package com.lianda.movies.data.api.sourceapi


import com.google.gson.annotations.SerializedName
import com.lianda.movies.domain.model.Review

data class ReviewSourceApi(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?
){
    fun toReview():Review{
        return Review(
            author = author.orEmpty(),
            content = content.orEmpty(),
            id = id.orEmpty(),
            url = url.orEmpty()
        )
    }
}