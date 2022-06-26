package com.camo.app.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.R
import com.camo.app.databinding.ItemProfilePostTitleBinding
import com.camo.app.model.UsersPosts

class ProfilePostTitleAdapter:ListAdapter<UsersPosts, ProfilePostTitleAdapter.PostTitleViewHolder> (PostTitleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostTitleViewHolder {
        val binding = ItemProfilePostTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostTitleViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: PostTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostTitleViewHolder(val context: Context, private val binding:ItemProfilePostTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usersPosts: UsersPosts?) {
            binding.usersPosts = usersPosts
        }

    }

}

class PostTitleDiffCallback : DiffUtil.ItemCallback<UsersPosts>() {
    override fun areItemsTheSame(oldItem: UsersPosts, newItem: UsersPosts): Boolean {
        return oldItem.usersPostsTitle == newItem.usersPostsTitle
    }

    override fun areContentsTheSame(oldItem: UsersPosts, newItem: UsersPosts): Boolean {
        return oldItem == newItem
    }

}
