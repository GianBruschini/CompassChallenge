package com.conexa.conexachallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConexaChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}