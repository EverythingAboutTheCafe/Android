package com.camo.app.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemPostBinding
import com.camo.app.model.Post
import com.google.android.material.tabs.TabLayoutMediator

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val timelineImageAdapter = PostImageAdapter()
        private val vpTLImg = binding.viewpagerTimelineImage

        init {
            vpTLImg.adapter = timelineImageAdapter
            TabLayoutMediator(binding.viewpagerTimelineImageIndicator, vpTLImg) { tab, position ->
            }.attach()
        }

        fun bind(post: Post) {
            binding.post = post
            timelineImageAdapter.submitList(post.postImages)
            binding.executePendingBindings()
        }

    }
}

