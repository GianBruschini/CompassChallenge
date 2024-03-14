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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback


class MapFragment : BaseFragment<FragmentMapBinding>(
    FragmentMapBinding::inflate,
), OnMapReadyCallback {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onMapReady(p0: GoogleMap) {

    }

}