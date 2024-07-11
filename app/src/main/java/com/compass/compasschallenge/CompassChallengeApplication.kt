package com.compass.compasschallenge

import android.app.Application
import com.compass.compasschallenge.util.NetworkUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CompassChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkUtils.init(this)
    }
}