package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class CompletableSchedulerTransformer(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : CompletableTransformer{

    override fun apply(upstream: Completable): CompletableSource =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}