package com.compass.compasschallenge.data.di

import android.content.Context

import com.compass.compasschallenge.data.feature.users.ContentLocalDataSource
import com.compass.compasschallenge.data.feature.users.ContentRemoteDataSource
import com.compass.compasschallenge.data.feature.users.ContentRepository
import com.compass.compasschallenge.data.service.ApiClient
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
    fun provideLocalContentDataSource(
        @ApplicationContext appContext: Context,
        gson: Gson,
    ): ContentLocalDataSource {
        return ContentLocalDataSource(appContext, gson)
    }



    @Provides
    @Singleton
    fun provideRemoteContentDataSource(
        apiClient: ApiClient,
        gson: Gson,
    ): ContentRemoteDataSource {
        return ContentRemoteDataSource(apiClient, gson)
    }



    @Provides
    @Singleton
    fun provideContentRepository(
        contentRemoteDataSource: ContentRemoteDataSource,
        contentLocalDataSource: ContentLocalDataSource,
    ): ContentRepository {
        return ContentRepository(contentRemoteDataSource, contentLocalDataSource)
    }
}