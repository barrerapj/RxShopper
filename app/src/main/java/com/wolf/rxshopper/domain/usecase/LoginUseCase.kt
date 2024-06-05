package com.wolf.rxshopper.domain.usecase

import com.wolf.rxshopper.data.services.AuthenticationRepository
import com.wolf.rxshopper.data.utils.Outcome
import com.wolf.rxshopper.domain.models.Session
import com.wolf.rxshopper.domain.models.services.LoginRequest
import com.wolf.rxshopper.domain.models.services.LoginResponse
import io.reactivex.rxjava3.core.Single

interface LoginUseCase {
    suspend fun login(username: String, password: String): Single<Outcome<Session>>
}

class LoginUseCaseImpl(
    private val auth: AuthenticationRepository,
): LoginUseCase {
    override suspend fun login(username: String, password: String): Single<Outcome<Session>> {
        return Single.just(Outcome.Error(null))
    }
}