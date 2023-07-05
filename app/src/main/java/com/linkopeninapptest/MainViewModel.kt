package com.linkopeninapptest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.domain.use_cases.DashboardUseCase
import com.linkopeninapptest.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase
):ViewModel() {

    val successfull: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    val data: MutableLiveData<DashboardModel?> = MutableLiveData()

    fun getDashboardData() {
        dashboardUseCase.getDashboardData().onEach {result ->
            when(result) {
                is State.Loading-> {
                    Log.i("LoginViewModel","Loading")
                }
                is State.Error -> {
                    error.postValue("${result.message}")
                    successfull.postValue(false)
                    Log.i("LoginViewModel","Error ${result.message}")
                }
                is State.Success -> {
                    data.postValue(result.data)
                    successfull.postValue(true)
                    Log.i("LoginViewModel","Error ${result.data?.toString()}")
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}