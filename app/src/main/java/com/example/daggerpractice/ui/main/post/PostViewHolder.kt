package com.example.daggerpractice.ui.main.post

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerpractice.models.Post
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * Created by PR72510 on 20/7/20.
 */
class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var titleTextView = itemView.tvPost
    fun bind(post: Post) {
        titleTextView.text = post.body
    }
}