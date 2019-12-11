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

    internal val status: MutableLiveData<Status> = MutableLiveData()
    val postList: MutableLiveData<ArrayList<PostData>> = MutableLiveData()
    var lastPost: String = ""
    val MAX_SIZE = 50

    fun getPostList() {
        PostModel.instance.getTopList().enqueue(object : Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                status.value = Status.ERROR
            }

            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                lastPost = response.body()?.data?.after ?: ""
                if (response.body()?.data?.children.isNullOrEmpty()) {
                    status.value = Status.EMPTY_LIST
                } else {
                    postList.value = response.body()?.data?.children as ArrayList<PostData>
                }
            }
        })
    }

    fun getNextPostList() {
        if (postList.value?.size ?: MAX_SIZE < MAX_SIZE) {
            PostModel.instance.getNextPage(lastPost).enqueue(object : Callback<RedditResponse> {
                override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                    status.value = Status.ERROR
                }

                override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                    lastPost = response.body()?.data?.after ?: ""
                    if (response.body()?.data?.children.isNullOrEmpty()) {
                        status.value = Status.EMPTY_LIST
                    } else {
                        postList.value?.addAll(response.body()?.data?.children as ArrayList<PostData>)
                        postList.value = postList.value
                    }
                }
            })
        } else {
            status.value = Status.FULL_LIST
        }

    }

    enum class Status {
        FULL_LIST,
        EMPTY_LIST,
        ERROR
    }

}