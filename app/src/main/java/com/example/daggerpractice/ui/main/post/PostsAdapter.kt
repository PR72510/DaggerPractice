package com.example.daggerpractice.ui.main.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerpractice.R
import com.example.daggerpractice.models.Post

/**
 * Created by PR72510 on 20/7/20.
 */
class PostsAdapter: RecyclerView.Adapter<PostViewHolder>() {

    var posts: List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}