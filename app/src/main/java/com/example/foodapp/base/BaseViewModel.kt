package com.example.foodapp.base

import androidx.lifecycle.ViewModel
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    var error = SingleLiveEvent<String>()

    inline fun <reified T> safeNetworkOperation(
        apiCall: () -> T
    ): T? {
        return try {
            apiCall()
        } catch (throwable: Throwable) {
            error.value = handleApiCallException(throwable)
            null
        }
    }

    fun handleApiCallException(throwable: Throwable): String {
        when (throwable) {
            is SocketTimeoutException -> {
                return "It took a long time to get the data, please check back later"
            }
            is UnknownHostException, is ConnectException -> {
                return "Please check your internet connection"
            }
            else -> {
                return "Please try again"
            }
        }
    }

}