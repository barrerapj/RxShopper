package com.wolf.rxshopper.data.extensions

import com.wolf.rxshopper.data.utils.SchedulerProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

object RxExtensions {
    object RxExtensions {
        /**
         * Assign compute thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Flowable<T>
         * @param provider SchedulerProvider
         * @return Flowable<T>
         */
        fun <T> Observable<T>.compute(provider: SchedulerProvider): Observable<T> =
            subscribeOn(provider.computationScheduler())
                .observeOn(provider.uiScheduler())

        /**
         * Assign io thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Flowable<T>
         * @param provider SchedulerProvider
         * @return Flowable<T>
         */
        fun <T> Flowable<T>.io(provider: SchedulerProvider): Flowable<T> =
            subscribeOn(provider.ioScheduler())
                .observeOn(provider.uiScheduler())

        /**
         * Assign compute thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Flowable<T>
         * @param provider SchedulerProvider
         * @return Flowable<T>
         */
        fun <T> Flowable<T>.compute(provider: SchedulerProvider): Flowable<T> =
            subscribeOn(provider.computationScheduler())
                .observeOn(provider.uiScheduler())

        /**
         * Assign io thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Single<T>
         * @param provider SchedulerProvider
         * @return Single<T>
         */
        fun <T> Single<T>.io(provider: SchedulerProvider): Single<T> =
            subscribeOn(provider.ioScheduler())
                .observeOn(provider.uiScheduler())

        /**
         * Assign compute thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Single<T>
         * @param provider SchedulerProvider
         * @return Single<T>
         */
        fun <T> Single<T>.compute(provider: SchedulerProvider): Single<T> =
            subscribeOn(provider.computationScheduler())
                .observeOn(provider.uiScheduler())

        /**
         * Assign io thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Completable
         * @param provider SchedulerProvider
         * @return Completable
         */
        fun Completable.io(provider: SchedulerProvider): Completable =
            subscribeOn(provider.ioScheduler()).observeOn(provider.uiScheduler())

        /**
         * Assign compute thread to subscribe on and ui thread to observe on
         * Both provided by the SchedulerProvider
         * @receiver Completable
         * @param provider SchedulerProvider
         * @return Completable
         */
        fun Completable.compute(provider: SchedulerProvider): Completable =
            subscribeOn(provider.ioScheduler())
                .observeOn(provider.uiScheduler())

    }
}