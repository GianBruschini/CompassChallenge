package com.conexa.conexachallenge

import android.app.Application
import com.conexa.conexachallenge.util.NetworkUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConexaChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkUtils.init(this)
    }
}