package com.barcodeapp.data.repository.datasourceImpl

import com.linkopeninapptest.api.LinkOpenninApi
import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.domain.repository.LinkOpeninRepository
import com.linkopeninapptest.repository.datasource.LinkeOpeninRemoteDataSource
import com.linkopeninapptest.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class LinkOpeninRemoteDataSourceImpl @Inject constructor(
    private val linkOpenninApi: LinkOpenninApi
) : LinkeOpeninRemoteDataSource {

    override suspend fun getDashboardData(): Response<DashboardModel> {
        return linkOpenninApi.GetDashboardData(
            token = Constants.token
        )
    }
}