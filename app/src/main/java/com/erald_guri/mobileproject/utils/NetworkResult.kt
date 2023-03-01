package com.erald_guri.mobileproject.utils

data class NetworkResult<out T>(val status: ApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): NetworkResult<T> = NetworkResult(status = ApiStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): NetworkResult<T> =
            NetworkResult(status = ApiStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): NetworkResult<T> = NetworkResult(status = ApiStatus.LOADING, data = data, message = null)
    }
}
