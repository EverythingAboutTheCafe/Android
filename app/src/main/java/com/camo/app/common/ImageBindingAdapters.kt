package com.camo.app.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.camo.app.GlideApp
import com.camo.app.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl:String?) {
    if(!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("isLike")
fun setLikeIcon(view: ImageView, isLike:Boolean) {
    if(isLike) {
        view.setImageResource(R.drawable.ic_like_on_icon)
    } else {
        view.setImageResource(R.drawable.ic_like_off_icon)
    }
}