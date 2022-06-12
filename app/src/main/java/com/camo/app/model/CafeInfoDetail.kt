package com.camo.app.model

data class CafeInfoDetail(
    val placenamefull: String,
    val cid: Int,
    val phonenum: String,
    val homepage: String,
    val addrdetail: String,
    val timeList: TimeList
)

data class TimeList(
    val timeSE: String,
    val dayOfWeek: String
)