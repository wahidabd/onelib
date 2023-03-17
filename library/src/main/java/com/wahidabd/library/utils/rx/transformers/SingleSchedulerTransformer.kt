package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class SingleSchedulerTransformer<T : Any>(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}