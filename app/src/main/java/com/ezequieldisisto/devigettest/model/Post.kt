package com.ezequieldisisto.devigettest.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(val title: String? = null,
           val author: String? = null,
           val created: Long? = null,
           val thumbnail: String? = null,
           val url: String? = null,
           @Json(name = "num_comments") val commentsAmount: Long? = null,
           val id: String)