package com.camo.app.repository

import android.util.Log
import com.camo.app.AssetLoader
import com.camo.app.model.Timeline
import com.google.gson.Gson

class TimelineAssetDataSource(private val assetLoader: AssetLoader) : TimelineDataSource{

    private val gson = Gson()

    override fun getTimeline(): Timeline? {
        return assetLoader.getJsonString("timeline.json")?.let { timelineJsonString ->
            gson.fromJson(timelineJsonString, Timeline::class.java)
        }
    }
}