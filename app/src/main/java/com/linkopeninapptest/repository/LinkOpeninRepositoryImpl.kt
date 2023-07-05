package com.linkopeninapptest.repository

import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.repository.datasource.LinkeOpeninRemoteDataSource
import com.linkopeninapptest.domain.repository.LinkOpeninRepository
import com.linkopeninapptest.utils.State
import javax.inject.Inject

class LinkOpeninRepositoryImpl @Inject constructor(
    private val linkopeninRemoteDatasource: LinkeOpeninRemoteDataSource) : LinkOpeninRepository {

    override suspend fun getDashboardData(): State<DashboardModel> {
        return responseToString(linkopeninRemoteDatasource.getDashboardData())
    }

    private fun responseToString(response: retrofit2.Response<DashboardModel>) : State<DashboardModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return State.Success(it)
            }
        }
        return State.Error(message = "${response.errorBody()?.string()}")
    }



}