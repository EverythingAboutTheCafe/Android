package com.camo.app.repository

import com.camo.app.api.KakaoApi
import com.camo.app.model.CafeInfo
import com.camo.app.utils.Constants.Companion.CAFE_CODE
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val kakaoApi: KakaoApi
) {

    suspend fun getNearbyCafes(
        radius: String,
        x: String,
        y: String,
        page: Int
    ): Response<CafeInfo> {
        return kakaoApi.getKakaoCafe(CAFE_CODE, radius, x, y, page)
    }

}