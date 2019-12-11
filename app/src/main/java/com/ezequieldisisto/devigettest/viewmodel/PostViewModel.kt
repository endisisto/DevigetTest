package com.ezequieldisisto.devigettest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezequieldisisto.devigettest.model.PostData
import com.ezequieldisisto.devigettest.model.RedditResponse
import com.ezequieldisisto.devigettest.networking.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    val postList: MutableLiveData<ArrayList<PostData>> = MutableLiveData()

    fun getPostList() {
        PostModel.instance.getTopList().enqueue(object : Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                postList.value = response.body()?.data?.children as ArrayList<PostData>
            }
        })
    }
}