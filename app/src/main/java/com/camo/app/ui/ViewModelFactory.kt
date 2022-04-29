package com.camo.app.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.camo.app.AssetLoader
import com.camo.app.repository.TimelineAssetDataSource
import com.camo.app.repository.TimelineRepository
import com.camo.app.ui.timeline.TimelineViewModel

class ViewModelFactory (private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TimelineViewModel::class.java)) {
            val repository = TimelineRepository(TimelineAssetDataSource(AssetLoader(context)))
            return TimelineViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }

}