package com.ezequieldisisto.devigettest.util

import androidx.recyclerview.widget.DiffUtil
import com.ezequieldisisto.devigettest.model.PostData

class PostListDiffCallback(var oldList: ArrayList<PostData>, var newList: ArrayList<PostData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].post?.id == oldList[oldItemPosition].post?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }
}