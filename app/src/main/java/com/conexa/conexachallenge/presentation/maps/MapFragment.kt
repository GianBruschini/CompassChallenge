package com.conexa.conexachallenge.presentation.maps

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conexa.conexachallenge.R
import com.conexa.conexachallenge.databinding.FragmentMapBinding
import com.conexa.conexachallenge.domain.feature.maps.interactor.MapContract
import com.conexa.conexachallenge.presentation.base.BaseFragment
import com.conexa.conexachallenge.util.BundleKeys
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate
) {

    private var userLat: String? = null
    private var userLng: String? = null
    @Inject
    lateinit var mapInteractor: MapContract
    private lateinit var mapFragment: SupportMapFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        initOnClicks()
        mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        initializeMap()
    }

    private fun initOnClicks() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getDataFromBundle() {
        userLat = arguments?.getString(BundleKeys.USER_LAT)
        userLng = arguments?.getString(BundleKeys.USER_LNG)
    }

    private fun initializeMap() {
        mapFragment.getMapAsync { map ->
            mapInteractor.initializeMap(map)
            addMarkersToMap()
        }
    }

    private fun addMarkersToMap() {

        mapInteractor.addMarker(
            latitude = userLat?.toDouble() ?: 0.0,
            longitude = userLng?.toDouble() ?: 0.0,
            markTitle = getString(R.string.map_user_location),
        )
        mapInteractor.animateCamera(
            latitude = userLat?.toDouble() ?: 0.0,
            longitude = userLng?.toDouble() ?: 0.0,
            animationTime = 4000,
            zoom = 10f,
        )
    }







}
