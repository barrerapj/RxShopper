package com.wolf.rxshopper.di

import com.wolf.rxshopper.data.services.AuthenticationRepository
import com.wolf.rxshopper.domain.usecase.LoginUseCase
import com.wolf.rxshopper.domain.usecase.LoginUseCaseImpl
import com.wolf.rxshopper.domain.usecase.RegisterUseCase
import com.wolf.rxshopper.domain.usecase.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideRegisterUseCase(
        authenticationRepository: AuthenticationRepository
    ): RegisterUseCase {
        return RegisterUseCaseImpl(authenticationRepository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        authenticationRepository: AuthenticationRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(authenticationRepository)
    }
}