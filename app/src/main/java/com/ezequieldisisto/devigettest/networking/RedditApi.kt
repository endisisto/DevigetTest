package com.ezequieldisisto.devigettest.networking

import com.ezequieldisisto.devigettest.model.RedditResponse
import retrofit2.Call
import retrofit2.http.GET

interface RedditApi {

    @GET("top.json?limit=10")
    fun getPostList(): Call<RedditResponse>
}