package com.example.lifecyclemaps

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.location.LocationManager
import android.location.LocationListener
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LocationLifecycleObserver(val ctx: Context, val vm: LocationViewModel) : DefaultLifecycleObserver, LocationListener {

    lateinit var locationManager: LocationManager
    var gpsPermission = false

     override fun onCreate(owner: LifecycleOwner) {
        locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }

    override fun onResume(owner: LifecycleOwner) {
        if(gpsPermission) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        if(gpsPermission) {
            locationManager.removeUpdates(this)
        }
    }

    override fun onLocationChanged(p0: Location) {
        vm.latLon = LatLon(p0.latitude, p0.longitude)
    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }
}