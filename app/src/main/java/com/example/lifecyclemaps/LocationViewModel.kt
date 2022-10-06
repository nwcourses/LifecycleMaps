package com.example.lifecyclemaps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class LatLon(var lat: Double=0.0, var lon: Double=0.0)

class LocationViewModel : ViewModel(){
    var latLon: LatLon = LatLon()
        set(value) {
            field = value
            liveLatLon.value = value
        }


    var liveLatLon: MutableLiveData<LatLon> = MutableLiveData()


    fun moveMapToLocation(place: String) {
        viewModelScope.launch {
            doSearch(place)
        }
    }

    private suspend fun doSearch(place: String) : LatLon {
        var response : String
        withContext(Dispatchers.IO) {
            response = URL("https://hikar.org/webapp/map/search?q=$place").readText()
        }
        val json = JSONObject(response)
        val lat = json.getDouble("lat")
        val lon = json.getDouble("lon")
        latLon = LatLon(lat, lon)
        return latLon
    }
}