package com.camo.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camo.app.model.CafeInfoDetail
import com.camo.app.model.CafeInfoUnit
import com.camo.app.model.Post
import com.camo.app.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val searchBartext: MutableLiveData<String> = MutableLiveData("노원구 공릉로")
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

    fun getCafeInfoDetail(cid: Int) {
        val cidString = cid.toString()
        val getUrl: String = "https://place.map.kakao.com/main/v/${cidString}"
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val doc: Document = Jsoup.connect(getUrl).ignoreContentType(true).get()

            doc.let { doc ->
                val tempBasicInfo = JSONObject(JSONObject(doc.body().html()).getString("basicInfo"))
                Log.d("suee97", "▶ cid : ${tempBasicInfo.getString("cid")}")

                try {
                    Log.d("suee97", "▶ phonenum : ${tempBasicInfo.getString("phonenum")}")
                } catch (e: Exception) {
                    Log.d("suee97", "▶ phonenum : 없음")
                }

                try {
                    Log.d("suee97", "▶ homepage : ${tempBasicInfo.getString("homepage")}")
                } catch (e: Exception) {
                    Log.d("suee97", "▶ homepage : 없음")
                }

                try {
                    val tempPeriodList = JSONObject(tempBasicInfo.getString("openHour"))
                        .getString("periodList")

                    Log.d("suee97", "${JSONArray(tempPeriodList)[0]}")
                } catch(e: Exception) {
                    Log.d("suee97", "없음")
                }

            }
        }
    }

}