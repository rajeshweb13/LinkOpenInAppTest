package com.linkopeninapptest.api

import com.linkopeninapptest.data.DashboardModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LinkOpenninApi {

    @GET("v1/dashboardNew")
    suspend fun GetDashboardData(@Header("Authorization") token:String): Response<DashboardModel>

}