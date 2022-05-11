package com.camo.app.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemTimelineBinding
import com.camo.app.model.Post
import com.google.android.material.tabs.TabLayoutMediator

class TimelineAdapter : ListAdapter<Post, TimelineAdapter.TimelineViewHolder>(TimelineDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TimelineViewHolder(private val binding: ItemTimelineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            with(binding.viewpagerTimelineImage) {
                adapter = TimeLinePostImageAdapter().build(post.postImages)
                TabLayoutMediator(binding.viewpagerTimelineImageIndicator, this) { tab, position ->
                }.attach()

            }
            binding.executePendingBindings()
        }
    }
}

class TimelineDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}