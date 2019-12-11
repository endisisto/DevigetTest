package com.ezequieldisisto.devigettest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezequieldisisto.devigettest.R
import com.ezequieldisisto.devigettest.model.PostData
import com.ezequieldisisto.devigettest.ui.adapter.viewholder.PostViewHolder

class PostAdapter(private val postList : ArrayList<PostData>) : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position].post

        post?.let {
            holder.title.text = it.title
            holder.author.text = it.author
            holder.postedTime.text = it.created.toString()
            holder.commentAmount.text = "${it.commentsAmount} ${context.getString(R.string.comments)}"

            if (it.thumbnail.isNullOrEmpty() || !URLUtil.isValidUrl(it.thumbnail)) {
                holder.thumbnail.visibility = View.GONE
            } else {
                holder.thumbnail.visibility = View.VISIBLE
                Glide.with(context).load(it.thumbnail).into(holder.thumbnail)
            }
        }
    }
}