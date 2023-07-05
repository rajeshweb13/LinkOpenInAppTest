package com.linkopeninapptest.di

import com.barcodeapp.data.repository.datasourceImpl.LinkOpeninRemoteDataSourceImpl
import com.linkopeninapptest.api.LinkOpenninApi
import com.linkopeninapptest.repository.datasource.LinkeOpeninRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLinkOpeninRemoteDataSource(linkopeninapi: LinkOpenninApi) : LinkeOpeninRemoteDataSource {
        return LinkOpeninRemoteDataSourceImpl(linkOpenninApi = linkopeninapi)
    }
}