package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer

class SingleSchedulerTransformer<T : Any>(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}