package com.camo.app.repository

import android.util.Log
import com.camo.app.model.Timeline

class TimelineRepository (
    private val assetDataSource: TimelineAssetDataSource
) {
    fun getTimeline(): Timeline? {
        Log.d("getTimeline()", "Tieline" + assetDataSource.getTimeline())
        return assetDataSource.getTimeline()
    }
}