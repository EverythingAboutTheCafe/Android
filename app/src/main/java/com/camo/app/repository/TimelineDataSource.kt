package com.camo.app.repository

import com.camo.app.model.Timeline

interface TimelineDataSource {
    fun getTimeline(): Timeline?
}