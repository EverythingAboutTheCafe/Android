package com.camo.app

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.graphics.Color
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 바텀바 + 네비게이션
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController =
            supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(it)
        }

        // GPS 확인
        if (checkLocationService()) {
            checkPermission()
        } else {
            Toast.makeText(this, "GPS 꺼져있음. 켜주세요~", Toast.LENGTH_SHORT).show()
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            Log.d("suee97", "onRequestPermissionResult function called")
        }
    }

    // Permission Check
    private fun checkPermission() {
        if ((ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) ==
                    PackageManager.PERMISSION_GRANTED) && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("suee97", "checkPermission : 권한이 있습니다.")
        } else {
            Log.d("suee97", "checkPermission : 권한이 없습니다. 권한을 요청합니다.")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    // GPS ON/OFF Check
    private fun checkLocationService(): Boolean {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        Log.d(
            "suee97",
            "checkLocationService() -> GPS : ${lm.isProviderEnabled(LocationManager.GPS_PROVIDER)}"
        )
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

}