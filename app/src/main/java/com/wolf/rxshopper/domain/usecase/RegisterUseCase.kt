package com.wolf.rxshopper.domain.usecase

import com.wolf.rxshopper.data.services.AuthenticationRepository
import com.wolf.rxshopper.data.utils.Resource
import com.wolf.rxshopper.data.utils.Status
import com.wolf.rxshopper.domain.models.UserRegister

interface RegisterUseCase {
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Resource<Unit>
}

class RegisterUseCaseImpl(
    private val auth: AuthenticationRepository
): RegisterUseCase {
    override suspend fun register(name: String, email: String, password: String): Resource<Unit> {
        return return Resource(Status.ERROR, null, null)
    }
}