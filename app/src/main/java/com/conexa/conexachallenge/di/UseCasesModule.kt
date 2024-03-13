package com.conexa.conexachallenge.di

import com.conexa.conexachallenge.data.feature.new.NewRepository
import com.conexa.conexachallenge.domain.usecase.new.GetNewsUseCase
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
    fun provideNewUseCase(
        newRepository: NewRepository,
    ): GetNewsUseCase {
        return GetNewsUseCase(newRepository)
    }


}
