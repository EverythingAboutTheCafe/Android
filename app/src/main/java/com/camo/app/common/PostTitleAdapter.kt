package com.camo.app.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemPostTitleBinding
import com.camo.app.model.Post

class PostTitleAdapter:ListAdapter<Post, PostTitleAdapter.PostTitleViewHolder>(PostDiffCallback()){
    class PostTitleViewHolder(private val binding: ItemPostTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostTitleAdapter.PostTitleViewHolder {
        val binding = ItemPostTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostTitleAdapter.PostTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
