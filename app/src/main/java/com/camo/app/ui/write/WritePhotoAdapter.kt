package com.camo.app.ui.write

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camo.app.databinding.ItemWritePhotoBinding
import com.camo.app.model.Images

class WritePhotoAdapter(private val items:ArrayList<Images>, val context: Context, val onDeleteClick:(image:Images) -> Unit) :
RecyclerView.Adapter<WritePhotoAdapter.WritePhotoViewHolder>(){


    class WritePhotoViewHolder(val binding: ItemWritePhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Images) {
            binding.image = image
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WritePhotoViewHolder {
        val binding = ItemWritePhotoBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        return WritePhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WritePhotoViewHolder, position: Int) {
        val listPosition = items[position]
        holder.bind(items[position])
        holder.binding.btnDeletePhoto.setOnClickListener {
            onDeleteClick.invoke(listPosition)
        }
    }

    override fun getItemCount(): Int = items.size

}

