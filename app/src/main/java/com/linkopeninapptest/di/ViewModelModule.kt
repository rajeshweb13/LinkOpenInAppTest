package com.linkopeninapptest.di

import com.linkopeninapptest.MainViewModel
import com.linkopeninapptest.domain.use_cases.DashboardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class
ViewModelModule {

    @Provides
    @Singleton
    fun providesLoginViewModel(dashboardUseCase: DashboardUseCase) : MainViewModel {
        return MainViewModel(dashboardUseCase = dashboardUseCase)
    }

}