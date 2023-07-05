package com.linkopeninapptest.di

import com.linkopeninapptest.domain.repository.LinkOpeninRepository
import com.linkopeninapptest.repository.LinkOpeninRepositoryImpl
import com.linkopeninapptest.repository.datasource.LinkeOpeninRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesLinkOpeninRepository(
        linkeOpeninRemoteDataSource: LinkeOpeninRemoteDataSource
    ): LinkOpeninRepository {
      return LinkOpeninRepositoryImpl(linkopeninRemoteDatasource = linkeOpeninRemoteDataSource)
    }

}