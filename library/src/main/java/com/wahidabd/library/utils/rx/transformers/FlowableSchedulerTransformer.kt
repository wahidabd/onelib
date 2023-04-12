package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.*
import org.reactivestreams.Publisher

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class FlowableSchedulerTransformer<T: Any>(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : FlowableTransformer<T, T>{

    override fun apply(upstream: Flowable<T>): Publisher<T> =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}