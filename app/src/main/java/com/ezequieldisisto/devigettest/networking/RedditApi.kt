package com.ezequieldisisto.devigettest.networking

import com.ezequieldisisto.devigettest.model.RedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json?limit=10")
    fun getPostList(): Call<RedditResponse>


    @GET("top.json?limit=10")
    fun getNextPostList(@Query("after") after: String): Call<RedditResponse>
}