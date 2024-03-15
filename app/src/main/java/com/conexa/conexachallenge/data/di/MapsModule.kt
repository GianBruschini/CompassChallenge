package com.conexa.conexachallenge.data.di

import com.conexa.conexachallenge.data.feature.maps.interactor.MapInteractor
import com.conexa.conexachallenge.domain.feature.maps.interactor.MapContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object MapsModule {

    @Provides
    @Singleton
    fun provideMapInteractor(): MapContract {
        return MapInteractor()
    }
}
