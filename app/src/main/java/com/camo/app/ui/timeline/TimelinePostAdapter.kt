package com.camo.app.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.common.PostDiffCallback
import com.camo.app.common.PostImageAdapter
import com.camo.app.databinding.ItemPostBinding
import com.camo.app.databinding.ItemPostCafeBinding
import com.camo.app.databinding.ItemPostTitleBinding
import com.camo.app.model.Post
import com.google.android.material.tabs.TabLayoutMediator

class TimelinePostAdapter : ListAdapter<Post, TimelinePostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemPostCafeBinding = ItemPostCafeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val itemPostTitleBinding = ItemPostTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(itemPostTitleBinding, itemPostBinding, itemPostCafeBinding)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(val titleBinding: ItemPostTitleBinding, var binding: ItemPostBinding, val cafeBinding: ItemPostCafeBinding) : RecyclerView.ViewHolder(binding.root) {
        private val timelineImageAdapter = PostImageAdapter()
        private val vpTLImg = binding.viewpagerTimelineImage
        private val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        private val bounceInterpolator = BounceInterpolator()

        init {
            vpTLImg.adapter = timelineImageAdapter
            binding.containerPostTitle.addView(titleBinding.root)
            binding.containerPostCafeTitle.addView(cafeBinding.root)
            TabLayoutMediator(binding.viewpagerTimelineImageIndicator, vpTLImg) { tab, position ->
            }.attach()
        }

        fun bind(post: Post) {
            binding.post = post
            titleBinding.post = post
            cafeBinding.cafe = post.cafe
            timelineImageAdapter.submitList(post.postImages)
            setLikeBtnAnimation()
            binding.executePendingBindings()
        }

        private fun setLikeBtnAnimation() {
            scaleAnimation.duration = 500
            scaleAnimation.interpolator = bounceInterpolator
            cafeBinding.btnPostCafeLike.setOnCheckedChangeListener { buttonView, isChecked ->
                buttonView.startAnimation(scaleAnimation)
            }
        }

    }
}