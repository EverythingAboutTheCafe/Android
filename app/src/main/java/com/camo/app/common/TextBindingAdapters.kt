package com.camo.app.common

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.camo.app.R


@BindingAdapter("congestion")
@SuppressLint("UseCompatLoadingForDrawables")
fun setTextView(view: TextView, congestion: Int) {
    if(congestion == 1){
        view.text="매우 한산"
        view.setTextColor(Color.parseColor("#E0FAB5"))
        view.background = view.context.getDrawable(R.drawable.background_light_green)
    }
    else if(congestion == 2){
        view.text="보통"
        view.setTextColor(Color.parseColor("#B5FAC4"))
        view.background = view.context.getDrawable(R.drawable.background_green)
    }
    else {
        view.text="다소 혼잡"
        view.setTextColor(Color.parseColor("#F16B4D"))
        view.background = view.context.getDrawable(R.drawable.background_orange)
    }
}

