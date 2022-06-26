package com.camo.app.common

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemCafeBinding
import com.camo.app.model.Cafe

class CafeAdapter : ListAdapter<Cafe, CafeAdapter.CafeAdapterViewHolder> (CafeDiffCallback()) {

    private lateinit var binding : ItemCafeBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeAdapterViewHolder {
        binding = ItemCafeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CafeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CafeAdapterViewHolder(private val binding : ItemCafeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cafe : Cafe) {
            binding.cafe = cafe
            binding.executePendingBindings()
        }
    }

}

class CafeDiffCallback :DiffUtil.ItemCallback<Cafe>(){
    override fun areItemsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
        return oldItem.cafeAddress == newItem.cafeAddress
    }

    override fun areContentsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
        return oldItem == newItem
    }

}
