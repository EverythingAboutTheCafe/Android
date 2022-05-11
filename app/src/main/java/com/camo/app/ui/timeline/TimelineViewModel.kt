package com.camo.app.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camo.app.model.Images
import com.camo.app.model.Post
import com.camo.app.repository.TimelineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val timelineRepository: TimelineRepository
    ) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        loadTimelineData()
    }

    private fun loadTimelineData() {
        val timelineData = timelineRepository.getTimeline()
        timelineData?.let { timelineData ->
            _posts.value = timelineData.posts
        }
    }
}