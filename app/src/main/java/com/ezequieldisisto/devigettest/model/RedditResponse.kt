package com.ezequieldisisto.devigettest.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditResponse(val data: Data? = null)