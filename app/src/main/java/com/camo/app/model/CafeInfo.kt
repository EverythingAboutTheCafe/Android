package com.camo.app.model

import com.google.gson.annotations.SerializedName

data class CafeInfo(
    @SerializedName("documents") val cafeInfos: List<CafeInfoUnit>,
    val meta: CafeMeta
)

data class CafeInfoUnit(
    val address_name: String,
    val category_group_code: String,
    val category_group_name: String,
    val category_name: String,
    val distance: String,
    val id: String,
    val phone: String,
    val place_name: String,
    val place_url: String,
    val road_address_name: String,
    val x: String, // 경도
    val y: String // 위도
)

data class CafeMeta(
    val is_end: Boolean,
    val pageable_count: Int,
    val same_name: RegionInfo?,
    val total_count: Int
)

data class RegionInfo(
    val region: Array<String>,
    val keyword: String,
    val selected_region: String
)