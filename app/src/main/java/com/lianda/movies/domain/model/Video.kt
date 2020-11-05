package com.lianda.movies.domain.model

import com.lianda.movies.utils.extentions.emptyString


data class Video(
    val id: String = emptyString(),
    val youtubeKey:String = emptyString()
)