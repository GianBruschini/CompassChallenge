package com.compass.compasschallenge.di


import com.compass.compasschallenge.data.feature.users.ContentRepository

import com.compass.compasschallenge.domain.usecase.users.GetContentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {



    @Provides
    @Singleton
    fun provideUserUseCase(
        contentRepository: ContentRepository,
    ): GetContentUseCase {
        return GetContentUseCase(contentRepository)
    }



}
