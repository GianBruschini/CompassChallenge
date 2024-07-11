package com.compass.compasschallenge.presentation

import android.os.Bundle
import com.compass.compasschallenge.R
import com.compass.compasschallenge.presentation.base.BaseActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
    }
}