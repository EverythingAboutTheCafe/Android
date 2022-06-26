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
            view.setTextColor(Color.parseColor("#E0FAB5"))
            view.background = view.context.getDrawable(R.drawable.background_light_green)
        }
        2 -> {
            view.text="보통"
            view.setTextColor(Color.parseColor("#B5FAC4"))
            view.background = view.context.getDrawable(R.drawable.background_green)
        }
        3 -> {
            view.text="다소 혼잡"
            view.setTextColor(Color.parseColor("#FE947D"))
            view.background = view.context.getDrawable(R.drawable.background_light_orange)
        }
        else -> {
            view.text="매우 혼잡"
            view.setTextColor(Color.parseColor("#F16B4D"))
            view.background = view.context.getDrawable(R.drawable.background_orange)
        }
    }
}

@BindingAdapter("postCount")
fun setPostCountText(view : TextView, postCount: Int) {
    view.text = view.context.getString(R.string.user_posts_count, postCount)
}

