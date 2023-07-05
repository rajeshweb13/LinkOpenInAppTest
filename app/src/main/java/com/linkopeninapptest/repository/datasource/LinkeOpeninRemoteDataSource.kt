package com.linkopeninapptest.repository.datasource

import com.linkopeninapptest.data.DashboardModel
import retrofit2.Response

interface LinkeOpeninRemoteDataSource {

    suspend fun getDashboardData():Response<DashboardModel>
}