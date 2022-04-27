package com.camo.app.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.camo.app.R
import com.google.android.gms.location.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPermission()
    }

    //권한
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
            isGranted ->
        if(isGranted) {
            //startProcess()
        } else {
            TODO("권한 거절시 처리")

        }

    }

    //onMapReady 연결
    private fun startProcess() {
        val fm = parentFragmentManager
        val mapFragment = fm.findFragmentById(R.id.home_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.home_map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    @UiThread
    override fun onMapReady(navermap: NaverMap) {
        val cameraPosition = CameraPosition(
            LatLng(37.5666102, 126.9783881), // 위치지정
            16.0 //줌 레벨
        )
        naverMap.cameraPosition = cameraPosition
        this.naverMap = naverMap

        var fusedLocationProviderClient = context?.let {
            LocationServices.getFusedLocationProviderClient(it)
        }
        setUpdateLocationListener()
    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback


    @SuppressLint("MissingPermission")
    private fun setUpdateLocationListener() {
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                for ((i, location) in locationResult.locations.withIndex()) {
                    Log.d("location: ", "${location.latitude}, ${location.longitude}")
                }
            }
        }

        Looper.myLooper()?.let {
            fusedLocationProviderClient
                .requestLocationUpdates(locationRequest, locationCallback, it)
        }

        fun setLastLocation(location: Location) {
            val myLocation = LatLng(location.latitude, location.longitude)
            val marker = Marker()
            marker.position = myLocation

            marker.map = naverMap

            val cameraUpdate = CameraUpdate.scrollTo(myLocation)
            naverMap.moveCamera(cameraUpdate)
            naverMap.maxZoom = 18.0
            naverMap.minZoom = 5.0
        }
    }
}