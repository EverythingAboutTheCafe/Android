package com.camo.app.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camo.app.model.CafeInfo
import com.camo.app.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val nearbyCafes: MutableLiveData<Response<CafeInfo>> = MutableLiveData()

    fun getNearbyCafes(
        radius: String,
        x: String,
        y: String,
        page: Int
    ) {
        viewModelScope.launch {
            val res = homeRepository.getNearbyCafes(radius, x, y, page)
            nearbyCafes.postValue(res)
            Log.d("suee97", "Nearby Cafe : ${res}")
        }
    }

}