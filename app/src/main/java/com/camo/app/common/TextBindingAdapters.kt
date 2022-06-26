package com.camo.app.common

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.camo.app.R


@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("congestion")
fun setTextView(view: TextView, congestion: Int) {
    when (congestion) {
        1 -> {
            view.text="널널함"
        }
        2 -> {
            view.text="보통"
        }
        3 -> {
            view.text="다소 혼잡"
        }
        else -> {
            view.text="매우 혼잡"
        }
    }
}

@BindingAdapter("postCount")
fun setPostCountText(view : TextView, postCount: Int) {
    view.text = view.context.getString(R.string.user_posts_count, postCount)
}

