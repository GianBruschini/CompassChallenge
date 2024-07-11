package com.compass.compasschallenge.data.feature.connectionerrors

import android.content.Context

object ErrorMessages {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }
    const val NO_DATA_AVAILABLE = "No data available"
}