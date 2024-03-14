package com.conexa.conexachallenge.presentation.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.conexa.conexachallenge.R
import com.conexa.conexachallenge.databinding.FragmentMapBinding
import com.conexa.conexachallenge.databinding.FragmentNewsBinding
import com.conexa.conexachallenge.presentation.adapters.NewsAdapter
import com.conexa.conexachallenge.presentation.base.BaseFragment
import com.conexa.conexachallenge.util.BundleKeys
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : BaseFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate
), OnMapReadyCallback {

    private var userLat: String? = null
    private var userLng: String? = null
    private var googleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun getDataFromBundle() {
        userLat = arguments?.getString(BundleKeys.USER_LAT)
        userLng = arguments?.getString(BundleKeys.USER_LNG)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        addMarkerToUserLocation()
    }

    private fun addMarkerToUserLocation() {
        userLat?.toDoubleOrNull()?.let { lat ->
            userLng?.toDoubleOrNull()?.let { lng ->
                val userLocation = LatLng(lat, lng)
                googleMap?.addMarker(
                    MarkerOptions()
                        .position(userLocation)
                        .title("User Location")
                )
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
            }
        }
    }
}
