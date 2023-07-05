package com.linkopeninapptest.domain.use_cases

import android.util.Log
import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.domain.repository.LinkOpeninRepository
import com.linkopeninapptest.utils.State
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val linkOpeninRepository: LinkOpeninRepository,
) {


    fun getDashboardData(): kotlinx.coroutines.flow.Flow<State<DashboardModel>> = flow {
        emit(State.Loading())
        try {
            val response = linkOpeninRepository.getDashboardData()
            Log.i("DashboardUseCase","one  ${response.data?.toString()}")
            emit(response)
        } catch (e: HttpException) {
            Log.i("DashboardUseCase", e.localizedMessage!!)
            emit(State.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e:IOException) {
            Log.i("DashboardUseCase", e.localizedMessage!!)
            emit(State.Error("Couldn't reach server. check your internet connection."))
        }
    }

}