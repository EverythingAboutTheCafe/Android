package com.camo.app.repository

import android.content.Context
import com.camo.app.AssetLoader
import com.camo.app.api.KakaoApi
import com.camo.app.model.CafeData
import com.camo.app.model.CafeInfo
import com.camo.app.model.Timeline
import com.camo.app.utils.Constants.Companion.CAFE_CODE
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val kakaoApi: KakaoApi,
    private val context: Context
) {

    suspend fun getNearbyCafes(radius: String, x: String, y: String, page: Int): Response<CafeInfo> {
        return kakaoApi.getKakaoCafe(CAFE_CODE, radius, x, y, page)
    }

}