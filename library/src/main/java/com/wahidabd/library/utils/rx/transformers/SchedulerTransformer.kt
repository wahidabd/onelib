package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

@Deprecated(message = "Please migrate to coroutine")
fun <T : Any> singleScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): SingleSchedulerTransformer<T> =
    SingleSchedulerTransformer(subscriberScheduler, observerScheduler)

@Deprecated(message = "Please migrate to coroutine")
fun <T : Any> observerScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): ObservableSchedulerTransformer<T> =
    ObservableSchedulerTransformer(subscriberScheduler, observerScheduler)

@Deprecated(message = "Please migrate to coroutine")
fun <T : Any> flowableScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): FlowableSchedulerTransformer<T> =
    FlowableSchedulerTransformer(subscriberScheduler, observerScheduler)

@Deprecated(message = "Please migrate to coroutine")
fun <T : Any> maybeScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): MaybeSchedulerTransformer<T> =
    MaybeSchedulerTransformer(subscriberScheduler, observerScheduler)

@Deprecated(message = "Please migrate to coroutine")
fun <T: Any> completableScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): CompletableSchedulerTransformer =
    CompletableSchedulerTransformer(subscriberScheduler, observerScheduler)