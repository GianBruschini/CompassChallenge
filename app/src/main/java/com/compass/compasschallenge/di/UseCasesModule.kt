package com.compass.compasschallenge.di


import com.compass.compasschallenge.data.feature.users.ContentRepository
import com.compass.compasschallenge.domain.usecase.content.GetWordCountUseCase

import com.compass.compasschallenge.domain.usecase.content.GetContentUseCase
import com.compass.compasschallenge.domain.usecase.content.GetEvery10thCharacterUseCase
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
    fun provideGetWordCountUseCase(
        contentRepository: ContentRepository,
    ): GetWordCountUseCase {
        return GetWordCountUseCase(contentRepository)
    }

    @Provides
    @Singleton
    fun provideGetEvery10thCharacterUseCase(
        contentRepository: ContentRepository,
    ): GetEvery10thCharacterUseCase {
        return GetEvery10thCharacterUseCase(contentRepository)
    }



}
