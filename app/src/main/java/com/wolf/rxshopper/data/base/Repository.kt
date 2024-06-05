package com.wolf.rxshopper.data.base

import com.wolf.rxshopper.domain.models.services.RequestError
import com.wolf.rxshopper.domain.models.services.ServiceError
import com.wolf.rxshopper.data.utils.ErrorType
import com.wolf.rxshopper.data.utils.Resource
import com.orhanobut.logger.Logger
import com.squareup.moshi.JsonAdapter
import com.wolf.rxshopper.data.utils.SchedulerProvider
import okio.BufferedSource
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

open class Repository {
    @Inject
    lateinit var provider: SchedulerProvider

    @Inject
    lateinit var errorAdapter: JsonAdapter<ServiceError>

    protected fun <T> onSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    protected fun <T> onError(
        source: BufferedSource?,
        adapter: JsonAdapter<ServiceError>? = null
    ): Resource<T> {
        return try {
            val error = adapter?.fromJson(source!!)
            requireNotNull(error)

            Resource.error(
                RequestError(
                    ErrorType.HTTP,
                    error.status,
                    error.error
                )
            )
        } catch (error: Exception) {
            Resource.error(RequestError())
        }
    }

    protected fun <T> onError(
        error: Throwable? = null
    ): Resource<T> {
        Logger.e("Error in request $error")
        return when(error) {
            is HttpException -> {
                Resource.error(
                    RequestError(
                        ErrorType.HTTP,
                        error.code()
                    )
                )
            }
            is ConnectException,
            is UnknownHostException,
            is SocketException,
            is SocketTimeoutException -> {
                Logger.d("Error connection.")
                Resource.error(
                    RequestError(
                        type = ErrorType.NETWORK,
                        message = "Network error."
                    )
                )
            }

            else -> {
                Logger.d("Error is unknown.")
                Resource.error(RequestError())
            }
        }
    }
}