package com.camo.app.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camo.app.model.Post
import com.camo.app.repository.TimelineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private var timelineRepository: TimelineRepository
    ) : ViewModel() {

    private var _posts = MutableLiveData<List<Post>>()
    var posts: LiveData<List<Post>> = _posts

    init {
        loadTimelineData()
    }

    private fun loadTimelineData() {
        var timelineData = timelineRepository.getTimeline()
        timelineData?.let { data ->
            _posts.value = data.posts
        }
    }
}