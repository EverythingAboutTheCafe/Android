package com.camo.app.ui.home


import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.camo.app.R
import com.camo.app.databinding.FragmentHomeBinding
import com.camo.app.ui.timeline.TimelineViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var locationManager: LocationManager
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        Log.d("suee97", "startTracking() function called")
        val userLocation: Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val userLatitude = userLocation?.latitude // 위도
        val userLongitude = userLocation?.longitude // 경도
        val userPosition = MapPoint.mapPointWithGeoCoord(userLatitude!!, userLongitude!!)
        Log.d("suee97", "User Location : 위도(${userLatitude})  경도(${userLongitude})")

        // 중심 변경
        mapView.setMapCenterPoint(userPosition, true)

        // 마커 표시 (파란색)
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

    }
}