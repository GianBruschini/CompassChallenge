package com.conexa.conexachallenge.data.di

import android.content.Context
import com.conexa.conexachallenge.data.feature.new.NewLocalDataSource
import com.conexa.conexachallenge.data.feature.new.NewRemoteDataSource
import com.conexa.conexachallenge.data.feature.new.NewRepository
import com.conexa.conexachallenge.data.service.ApiClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NewsModule {

    @Provides
    @Singleton
    fun provideLocalNewDataSource(
        @ApplicationContext appContext: Context,
        gson: Gson,
    ): NewLocalDataSource {
        return NewLocalDataSource(appContext, gson)
    }

    @Provides
    @Singleton
    fun provideRemoteNewDataSource(
        apiClient: ApiClient,
        gson: Gson,
    ): NewRemoteDataSource {
        return NewRemoteDataSource(apiClient, gson)
    }

    @Provides
    @Singleton
    fun provideNewRepository(
        userRemoteDataSource: NewRemoteDataSource,
        userLocalDataSource: NewLocalDataSource,
    ): NewRepository {
        return NewRepository(userRemoteDataSource, userLocalDataSource)
    }
}