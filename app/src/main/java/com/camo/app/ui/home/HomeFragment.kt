package com.camo.app.ui.home


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.camo.app.R
import com.camo.app.databinding.FragmentHomeBinding
import com.camo.app.utils.Constants.Companion.SMALL_RADIUS
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var locationManager: LocationManager
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.initializeCafeList()

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize locationManager
        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // for observing live data (현기준 필수 아님)
        binding.lifecycleOwner = viewLifecycleOwner

        // bottom sheet
        val behavior = BottomSheetBehavior.from(binding.homeBottomSheet)
        behavior.peekHeight = 72

        val mapView = MapView(activity)
        val mapViewContainer = view.findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)

        // 맵 띄우기
        initializeMap(mapView)
    }

    @SuppressLint("MissingPermission")
    private fun initializeMap(mapView: MapView) {

        // 위치 정보
        val userLocation: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val userLatitude = userLocation?.latitude // 위도
        val userLongitude = userLocation?.longitude // 경도
        val userPosition = MapPoint.mapPointWithGeoCoord(userLatitude!!, userLongitude!!)
        Log.d("suee97", "User Location : 위도(${userLatitude})  경도(${userLongitude})")

        // 중심, 줌레벨 변경
        mapView.setMapCenterPoint(userPosition, true)
        mapView.setZoomLevelFloat(2.5f, true)

        // 사용자 위치 마커 표시
        val userPosMarker = MapPOIItem()
        userPosMarker.itemName = "현 위치"
        userPosMarker.tag = 1
        userPosMarker.mapPoint = userPosition
        userPosMarker.markerType = MapPOIItem.MarkerType.CustomImage
        userPosMarker.customImageResourceId = R.drawable.map_marker // 벡터 이미지 지원x
        userPosMarker.isCustomImageAutoscale = false

        userPosMarker.setCustomImageAnchor(0.5f, 0.5f)
        userPosMarker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView.addPOIItem(userPosMarker)

        // 사용자 기준 반경 500m 표시
        val circle = MapCircle(
            MapPoint.mapPointWithGeoCoord(userLatitude, userLongitude),
            SMALL_RADIUS,
            Color.argb(128, 255, 0, 0),
            Color.argb(128, 0, 255, 0)
        )
        circle.tag = 1234
        mapView.addCircle(circle)

        // 주변 카페 불러오기 + 표시
        viewModel.getAllNearbyCafe(SMALL_RADIUS.toString(), userLongitude.toString(), userLatitude.toString())
        viewModel.cafeList.observe(this, Observer { res ->
            res.forEach {
                val tempMarker = MapPOIItem()
                tempMarker.itemName = it.place_name
                tempMarker.tag = 0
                tempMarker.mapPoint =
                    MapPoint.mapPointWithGeoCoord(it.y.toDouble(), it.x.toDouble())
                tempMarker.markerType = MapPOIItem.MarkerType.BluePin
                tempMarker.selectedMarkerType = MapPOIItem.MarkerType.BluePin
                mapView.addPOIItem(tempMarker)
            }
        })

    }
}