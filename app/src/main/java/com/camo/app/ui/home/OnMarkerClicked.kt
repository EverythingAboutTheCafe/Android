package com.camo.app.ui.home

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


// 마커 클릭 이벤트
class OnMarkerClicked(private val behavior: BottomSheetBehavior<ConstraintLayout>) :
    MapView.POIItemEventListener {

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        p1?.markerType = MapPOIItem.MarkerType.YellowPin
        Log.d("suee97", "아이템 클릭 : ${p1?.itemName}")
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        if (p1?.itemName != "현 위치") behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}

}