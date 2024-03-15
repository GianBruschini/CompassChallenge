package com.conexa.conexachallenge.data.feature.maps.interactor


import com.conexa.conexachallenge.domain.feature.maps.interactor.MapContract
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapInteractor : MapContract {
    private lateinit var googleMap: GoogleMap

    override fun initializeMap(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    override fun addMarker(latitude: Double, longitude: Double, markTitle: String) {
        if (::googleMap.isInitialized) {
            val coordinates = LatLng(latitude, longitude)
            val marker = MarkerOptions().position(coordinates).title(markTitle)
            googleMap.addMarker(marker)
        }
    }

    override fun animateCamera(latitude: Double, longitude: Double, animationTime: Int, zoom: Float) {
        if (::googleMap.isInitialized) {
            val coordinates = LatLng(latitude, longitude)
            val cameraAnimation = CameraUpdateFactory.newLatLngZoom(coordinates, zoom)
            googleMap.animateCamera(cameraAnimation, animationTime, null)
        }
    }
}
