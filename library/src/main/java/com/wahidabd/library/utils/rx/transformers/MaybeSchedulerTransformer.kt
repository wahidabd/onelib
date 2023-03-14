package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeSource
import io.reactivex.rxjava3.core.MaybeTransformer
import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class MaybeSchedulerTransformer<T: Any>(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : MaybeTransformer<T, T> {

    override fun apply(upstream: Maybe<T>): MaybeSource<T> =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}