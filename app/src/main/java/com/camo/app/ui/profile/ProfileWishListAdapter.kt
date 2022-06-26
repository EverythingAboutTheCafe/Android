package com.camo.app.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.common.CafeAdapter
import com.camo.app.databinding.ItemWishlistBinding
import com.camo.app.model.WishList

class ProfileWishListAdapter : ListAdapter<WishList, ProfileWishListAdapter.WishListViewHolder>(WishListDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val binding = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return WishListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WishListViewHolder(private val binding:ItemWishlistBinding) : RecyclerView.ViewHolder(binding.root) {
        private val cafeAdapter = CafeAdapter()
        private val rvProfileWishList = binding.rvProfileWishlist

        init {
            rvProfileWishList.adapter = cafeAdapter
        }

        fun bind(wishList: WishList?) {
            binding.wishlist = wishList
            cafeAdapter.submitList(wishList?.cafe)
            binding.executePendingBindings()
        }

    }

}

class WishListDiffCallback : DiffUtil.ItemCallback<WishList>() {

    override fun areItemsTheSame(oldItem: WishList, newItem: WishList): Boolean {
        return oldItem.cafe == newItem.cafe
    }

    override fun areContentsTheSame(oldItem: WishList, newItem: WishList): Boolean {
        return oldItem == newItem
    }

}
