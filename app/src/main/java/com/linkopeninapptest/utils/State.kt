package com.linkopeninapptest.utils

sealed class State<T>(
    val data:T? = null,
    val message:String?=null) {

    class Idle<T>: State<T>()
    class Loading<T>(data: T? = null): State<T>(data)
    class Success<T>(data: T): State<T>(data)
    class Error<T>(message: String, data: T?=null): State<T>(data, message)
}
