package com.conexa.conexachallenge.presentation.maps

import android.os.Bundle
import android.view.View
import com.conexa.conexachallenge.R
import com.conexa.conexachallenge.databinding.FragmentMapBinding
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
    private lateinit var mMap: GoogleMap

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = userLat?.let { userLng?.let { it1 -> LatLng(it.toDouble(), it1.toDouble()) } }
        mMap.addMarker(sydney?.let {
            MarkerOptions()
                .position(it)
                .title(getString(R.string.map_user_location))
        }!!)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}
