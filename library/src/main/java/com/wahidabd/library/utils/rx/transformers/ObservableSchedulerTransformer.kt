package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class ObservableSchedulerTransformer<T : Any>(
    private val subscriberScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : ObservableTransformer<T, T>{

    override fun apply(upstream: Observable<T>): ObservableSource<T> =
        upstream.subscribeOn(subscriberScheduler).observeOn(observerScheduler)

}