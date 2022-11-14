package com.testing.weatherapp.ui.splash

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.testing.weatherapp.R
import com.testing.weatherapp.ui.main.MainActivity
import com.testing.weatherapp.util.LocationRequests.checkPermission
import com.testing.weatherapp.util.LocationRequests.requestPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getUserLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation()
        } else {
            startMainScreen()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun startMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getUserLocation() {
        if (checkPermission(this)) {
            getLocation()
        } else {
            requestPermission(this)
        }
    }

    private fun getLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val latitude =  location?.latitude.toString()
                val longitude = location?.longitude.toString()
                viewModel.setCoordinates(latitude, longitude)
                startMainScreen()
            }
    }
}