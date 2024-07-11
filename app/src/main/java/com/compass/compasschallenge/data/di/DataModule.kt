package com.compass.compasschallenge.data.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal object DataModule {
    @Provides
    fun provideGson() = Gson()
}
