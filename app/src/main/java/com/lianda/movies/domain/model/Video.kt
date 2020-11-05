package com.lianda.movies.domain.model

import com.lianda.movies.utils.extentions.emptyString


data class Video(
    val id: String = emptyString(),
    val url:String = emptyString(),
    val yotubeKey:String = emptyString()
)