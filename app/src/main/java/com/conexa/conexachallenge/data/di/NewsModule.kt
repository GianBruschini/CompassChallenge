package com.conexa.conexachallenge.data.di

import android.content.Context
import com.conexa.conexachallenge.data.feature.report.NewLocalDataSource
import com.conexa.conexachallenge.data.feature.report.NewRemoteDataSource
import com.conexa.conexachallenge.data.feature.report.NewRepository
import com.conexa.conexachallenge.data.feature.users.UsersLocalDataSource
import com.conexa.conexachallenge.data.feature.users.UsersRemoteDataSource
import com.conexa.conexachallenge.data.feature.users.UsersRepository
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
    fun provideLocalUserDataSource(
        @ApplicationContext appContext: Context,
        gson: Gson,
    ): UsersLocalDataSource {
        return UsersLocalDataSource(appContext, gson)
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
    fun provideRemoteUserDataSource(
        apiClient: ApiClient,
        gson: Gson,
    ): UsersRemoteDataSource {
        return UsersRemoteDataSource(apiClient, gson)
    }

    @Provides
    @Singleton
    fun provideNewRepository(
        newsRemoteDataSource: NewRemoteDataSource,
        newsLocalDataSource: NewLocalDataSource,
    ): NewRepository {
        return NewRepository(newsRemoteDataSource, newsLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: UsersRemoteDataSource,
        userLocalDataSource: UsersLocalDataSource,
    ): UsersRepository {
        return UsersRepository(userRemoteDataSource, userLocalDataSource)
    }
}