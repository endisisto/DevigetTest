package com.ezequieldisisto.devigettest.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostData(@Json(name = "data") val post: Post? = null)