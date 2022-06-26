package com.camo.app.model

// -----------------------------------------------------------------------------
// 크롤링 이후 DB 에 저장되는 정보입니다.
// -----------------------------------------------------------------------------

data class CafeData(
    val id: Long,
    val name: String,
    val businessHours: String,
    val address: String,
    val phone: String,
    val menu: List<Pair<String, Int>>
)
