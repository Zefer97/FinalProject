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
                return "Data ni almaq uzun cekdi, zehmet olmasa daha sonra yoxlayarsiz"
            }
            is UnknownHostException, is ConnectException -> {
                return "Interternet baglantinizi yoxlayin zehmet olsada, olmasada"
            }
            else -> {
                return "Xeta bash verdi, yeniden cehd edin"
            }
        }
    }

}