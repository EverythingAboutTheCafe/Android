package com.camo.app.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemPostImageBinding
import com.camo.app.model.Images

class TimeLinePostImageAdapter:ListAdapter<Images, TimeLinePostImageAdapter.TimeLinePostImageViewHolder> (
    ImageDiffCallback()
        ) {

    private lateinit var binding: ItemPostImageBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLinePostImageViewHolder {
        binding = ItemPostImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeLinePostImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeLinePostImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TimeLinePostImageViewHolder(private val binding: ItemPostImageBinding) : RecyclerView.ViewHolder(binding.root) {
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