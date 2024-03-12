package com.conexa.conexachallenge.domain.feature.maps.interactor

import com.google.android.gms.maps.GoogleMap


interface MapContract {
    fun initializeMap(googleMap: GoogleMap)
    fun addMarker(latitude: Double, longitude: Double, markTitle: String)
    fun animateCamera(latitude: Double, longitude: Double, animationTime: Int, zoom: Float)
}
