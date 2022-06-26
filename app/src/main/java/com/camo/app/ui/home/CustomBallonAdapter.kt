package com.camo.app.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.camo.app.R
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem

class CustomBallonAdapter(private val inflater: LayoutInflater): CalloutBalloonAdapter {

    private lateinit var mCalloutBallon: View

    override fun getCalloutBalloon(p0: MapPOIItem?): View {
        mCalloutBallon = inflater.inflate(R.layout.item_callout_ballon, null)
        mCalloutBallon.findViewById<TextView>(R.id.tv_ballon_text).text = p0?.itemName
        return mCalloutBallon
    }

    override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
        return mCalloutBallon
    }
}