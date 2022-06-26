package com.camo.app.repository

import android.content.Context
import com.camo.app.AssetLoader
import com.camo.app.model.Timeline
import com.google.gson.Gson
import javax.inject.Inject

class TimelineRepository @Inject constructor(
    private val context: Context
) {

    private val gson = Gson()
    private val assetLoader = AssetLoader(context)

    fun getTimeline(): Timeline? {
        return assetLoader.getJsonString("timeline.json")?.let { timelineJsonString ->
            gson.fromJson(timelineJsonString, Timeline::class.java)
        }
    }
}