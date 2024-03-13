package com.conexa.conexachallenge.presentation

import android.os.Bundle
import com.conexa.conexachallenge.R
import com.conexa.conexachallenge.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
    }
}