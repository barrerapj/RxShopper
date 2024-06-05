package com.wolf.rxshopper.data.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

interface SchedulerProvider {
    fun ioScheduler(): Scheduler
    fun uiScheduler(): Scheduler
    fun computationScheduler(): Scheduler
}

@Singleton
class DefaultSchedulerProvider : SchedulerProvider {
    override fun ioScheduler() = Schedulers.io()
    override fun uiScheduler() = AndroidSchedulers.mainThread()
    override fun computationScheduler() = Schedulers.computation()
}