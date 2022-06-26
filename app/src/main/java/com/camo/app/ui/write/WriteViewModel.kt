package com.camo.app.ui.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camo.app.model.Images

class WriteViewModel: ViewModel() {
    private val _images = MutableLiveData<List<Images>>()
    val images : LiveData<List<Images>> = _images
    fun setImages(images : List<Images>) {
        _images.value = images
    }

    private val _visitTime = MutableLiveData<String>()
    val visitTime : LiveData<String> = _visitTime

    fun setVisitTime(visitTime: String) {
        _visitTime.value = visitTime
    }
}