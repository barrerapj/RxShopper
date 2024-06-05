package com.wolf.rxshopper.di

import com.wolf.rxshopper.data.services.AuthenticationAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideAuthenticationAPI(retrofit: Retrofit): AuthenticationAPI {
        return retrofit.create(AuthenticationAPI::class.java)
    }
}