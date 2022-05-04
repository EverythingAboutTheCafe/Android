package com.camo.app.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camo.app.model.CafeInfoUnit
import com.camo.app.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val cafeList: MutableLiveData<List<CafeInfoUnit>> = MutableLiveData()
    val tempList: MutableList<CafeInfoUnit> = mutableListOf()

    fun initializeCafeList() {
        cafeList.postValue(listOf())
    }

    fun getAllNearbyCafe(radius: String, x: String, y: String) {
        tempList.clear()

        viewModelScope.launch {
            val totalCount = getNearbyCafe(radius, x, y, 1)
            var page = 1
            while(totalCount > tempList.size) {
                getNearbyCafe(radius, x, y, ++page)
            }
            Log.d("suee97", "검색 된 카페 수 : ${tempList.size}")
            cafeList.postValue(tempList)
        }
    }

    suspend fun getNearbyCafe(radius: String, x: String, y: String, page: Int): Int {
        val res = homeRepository.getNearbyCafes(radius, x, y, page)
        Log.d("suee97", "getNearbyCafe API 호출")
        tempList.addAll(res.body()?.cafeInfos as Collection<CafeInfoUnit>)

        return res.body()?.meta?.pageable_count?:0
    }

}