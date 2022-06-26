package com.camo.app.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemPostCafeBinding
import com.camo.app.model.Post

class PostCafeAdapter : ListAdapter<Post, PostCafeAdapter.PostCafeViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCafeViewHolder {
        val binding = ItemPostCafeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostCafeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostCafeViewHolder, position: Int) {
        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        val bounceInterpolator = BounceInterpolator()

        holder.bind(getItem(position))
        scaleAnimation.duration = 500
        scaleAnimation.interpolator = bounceInterpolator

        holder.binding.btnPostCafeLike.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.startAnimation(scaleAnimation)
        }

    }

    class PostCafeViewHolder(val binding:ItemPostCafeBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.cafe = post.cafe
            binding.executePendingBindings()
        }

    }

}
