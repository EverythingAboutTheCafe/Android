package com.camo.app.api

import com.camo.app.model.CafeInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {

    // 카페 정보 가져오기(page 포함)
    @Headers("Authorization: KakaoAK 4c28924e0838673d5f54f4d97ecff49d")
    @GET("category.json")
    suspend fun getKakaoCafe(
        @Query("category_group_code") category_group_code: String,
        @Query("radius") radius: String,
        @Query("x") x: String,
        @Query("y") y: String,
        @Query("page") page: Int
    ): Response<CafeInfo>

}