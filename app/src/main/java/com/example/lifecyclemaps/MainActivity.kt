package com.example.lifecyclemaps

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var lifecycleObserver : LocationLifecycleObserver
    val viewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleObserver = LocationLifecycleObserver(this, viewModel)
        requestPermissions()
        lifecycle.addObserver(lifecycleObserver)
    }

    fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, LOCATION_SERVICE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            lifecycleObserver.gpsPermission = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("GPS granted").show()
            lifecycleObserver.gpsPermission = true
        } else {
            AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("GPS denied").show()
            lifecycleObserver.gpsPermission = false
        }
    }
}