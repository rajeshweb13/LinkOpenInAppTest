package com.linkopeninapptest.domain.repository

import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.utils.State
import retrofit2.Response

interface LinkOpeninRepository {
    suspend fun getDashboardData(): State<DashboardModel>
}