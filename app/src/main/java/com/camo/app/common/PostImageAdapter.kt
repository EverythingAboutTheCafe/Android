package com.camo.app.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemPostImageBinding
import com.camo.app.model.Images

class PostImageAdapter:ListAdapter<Images, PostImageAdapter.PostImageViewHolder> (
    ImageDiffCallback()
        ) {

    private lateinit var binding: ItemPostImageBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        binding = ItemPostImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostImageViewHolder(private val binding: ItemPostImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(images: Images) {
            binding.images = images
            binding.executePendingBindings()
        }

    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<Images>() {
    override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem.postImageUrl == newItem.postImageUrl
    }

    override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem == newItem
    }

}