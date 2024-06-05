package com.wolf.rxshopper.data.utils

import com.wolf.rxshopper.domain.models.services.RequestError

data class Resource<out T>(val status: Status, val data: T?, val error: RequestError?) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: RequestError): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}

sealed class Outcome<T> {
    data class Success<T>(var data: T?): Outcome<T>()
    data class Error<T>(var error: RequestError?): Outcome<T>()
}