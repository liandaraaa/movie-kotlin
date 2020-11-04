package com.lianda.movies.data.api.entities


import com.google.gson.annotations.SerializedName

data class GenreSourceApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)