package com.lianda.movies.data.api.sourceapi


import com.google.gson.annotations.SerializedName

data class GenreSourceApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)