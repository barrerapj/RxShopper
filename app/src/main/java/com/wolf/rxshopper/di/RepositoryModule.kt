package com.wolf.rxshopper.di

import com.squareup.moshi.JsonAdapter
import com.wolf.rxshopper.data.mapper.LoginResponseToSessionMapper
import com.wolf.rxshopper.data.mapper.UserRegisterToUserRegisterRequest
import com.wolf.rxshopper.data.services.AuthenticationAPI
import com.wolf.rxshopper.data.services.AuthenticationRepository
import com.wolf.rxshopper.data.services.AuthenticationRepositoryImpl
import com.wolf.rxshopper.domain.models.services.ServiceError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        api: AuthenticationAPI,
        errorAdapter: JsonAdapter<ServiceError>,
        requestMapper: UserRegisterToUserRegisterRequest,
        loginResponseToSessionMapper: LoginResponseToSessionMapper
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            api
        )
    }
}