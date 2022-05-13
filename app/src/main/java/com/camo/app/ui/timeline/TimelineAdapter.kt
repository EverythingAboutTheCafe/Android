package com.camo.app.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemTimelineBinding
import com.camo.app.model.Post
import com.google.android.material.tabs.TabLayoutMediator

class TimelineAdapter : ListAdapter<Post, TimelineAdapter.TimelineViewHolder>(TimelineDiffCallback()) {

    private val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
    private val bounceInterpolator = BounceInterpolator()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(getItem(position))

        scaleAnimation.duration = 500
        scaleAnimation.interpolator = bounceInterpolator

        holder.binding.btnCafeLike.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.startAnimation(scaleAnimation)
        }

    }

    class TimelineViewHolder(val binding: ItemTimelineBinding) : RecyclerView.ViewHolder(binding.root) {
        val timelineImageAdapter = TimeLinePostImageAdapter()
        val vpTLImg = binding.viewpagerTimelineImage

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

class TimelineDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}