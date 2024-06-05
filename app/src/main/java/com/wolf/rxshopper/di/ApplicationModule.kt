package com.wolf.rxshopper.di

import android.content.Context
import com.wolf.rxshopper.data.base.SharedPreferencesUtils.newEncryptedSharedPreferences
import com.wolf.rxshopper.data.base.SharedPreferencesUtils.newSharedPreferences
import com.wolf.rxshopper.data.preferences.PreferencesRepository
import com.wolf.rxshopper.data.preferences.PreferencesRepositoryImpl
import com.wolf.rxshopper.data.utils.DefaultSchedulerProvider
import com.wolf.rxshopper.data.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun providePreferencesRepository(context: Context): PreferencesRepository {
        return PreferencesRepositoryImpl(
            context.newSharedPreferences(),
            context.newEncryptedSharedPreferences()
        )
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return DefaultSchedulerProvider()
    }
}