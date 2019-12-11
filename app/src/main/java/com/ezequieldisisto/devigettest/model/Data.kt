package com.ezequieldisisto.devigettest.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(val children: List<PostData>? = null,
           val after: String? = null)