package com.ezequieldisisto.devigettest.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ezequieldisisto.devigettest.R

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.title)
    val author: TextView = itemView.findViewById(R.id.author)
    val postedTime: TextView = itemView.findViewById(R.id.postedTime)
    val commentAmount: TextView = itemView.findViewById(R.id.comments)
    val btnDismiss: TextView = itemView.findViewById(R.id.btnDismiss)
    val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
}