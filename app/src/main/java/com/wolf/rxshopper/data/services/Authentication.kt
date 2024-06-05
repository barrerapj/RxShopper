package com.wolf.rxshopper.data.services

import com.orhanobut.logger.Logger
import com.squareup.moshi.JsonAdapter
import com.wolf.rxshopper.data.base.Repository
import com.wolf.rxshopper.data.extensions.RxExtensions.RxExtensions.io
import com.wolf.rxshopper.data.utils.ErrorType
import com.wolf.rxshopper.data.utils.Outcome
import com.wolf.rxshopper.domain.models.services.*
import io.reactivex.rxjava3.core.Single
import okio.BufferedSource
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface AuthenticationAPI {
    @POST("/register")
    suspend fun register(@Body data: UserRegisterRequest): Single<Response<Unit>>

    @POST("/login")
    suspend fun login(@Body data: LoginRequest): Single<Response<LoginResponse>>
}

interface AuthenticationRepository  {
    suspend fun register(data: UserRegisterRequest): Single<Outcome<Unit?>>
    suspend fun login(data: LoginRequest): Single<Outcome<LoginResponse?>>
}

class AuthenticationRepositoryImpl(
    private val api: AuthenticationAPI
): Repository(), AuthenticationRepository {
    override suspend fun register(data: UserRegisterRequest): Single<Outcome<Unit?>> {
        return api.register(data)
            .io(provider)
            .map {
                if (it.isSuccessful) {
                    success(it.body())
                } else {
                    error(it.errorBody()?.source(), errorAdapter)
                }
            }
            .onErrorReturn { error(it) }
    }

    override suspend fun login(data: LoginRequest): Single<Outcome<LoginResponse?>> {
        return api
            .login(data)
            .io(provider)
            .map {
                if (it.isSuccessful) {
                    success(it.body())
                } else {
                    error(it.errorBody()?.source(), errorAdapter)
                }
            }
            .onErrorReturn { error(it) }
    }

    protected fun <T> success(data: T): Outcome<T> {
        return Outcome.Success(data)
    }

    protected fun <T> error(
        source: BufferedSource?,
        adapter: JsonAdapter<ServiceError>? = null
    ): Outcome<T> {
        return try {
            val error = adapter?.fromJson(source!!)
            requireNotNull(error)

            Outcome.Error(
                RequestError(
                    ErrorType.HTTP,
                    error.status,
                    error.error
                )
            )
        } catch (error: java.lang.Exception) {
            Outcome.Error(RequestError())
        }
    }

    protected fun <T> error(
        error: Throwable? = null
    ): Outcome<T> {
        Logger.e("Error in request $error")
        return when(error) {
            is HttpException -> {
                Outcome.Error(
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
                Outcome.Error(
                    RequestError(
                        type = ErrorType.NETWORK,
                        message = "Network error."
                    )
                )
            }

            else -> {
                Logger.d("Error is unknown.")
                Outcome.Error(RequestError())
            }
        }
    }
}