package com.lianda.movies.data.api.entities


import com.google.gson.annotations.SerializedName

data class VideoListSourceApi(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val videoSourceApis: List<VideoSourceApi>?
)