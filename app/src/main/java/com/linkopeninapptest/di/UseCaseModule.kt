package com.linkopeninapptest.di

import com.linkopeninapptest.domain.repository.LinkOpeninRepository
import com.linkopeninapptest.domain.use_cases.DashboardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesAuthUseCase(linkOpeninRepository: LinkOpeninRepository): DashboardUseCase {
        return DashboardUseCase(linkOpeninRepository = linkOpeninRepository)
    }

}