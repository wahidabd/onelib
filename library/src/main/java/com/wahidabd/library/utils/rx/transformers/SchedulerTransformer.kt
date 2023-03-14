package com.wahidabd.library.utils.rx.transformers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T : Any> singleScheduler(
    subscriberScheduler: Scheduler = Schedulers.io(),
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): SingleSchedulerTransformer<T> =
    SingleSchedulerTransformer(subscriberScheduler, observerScheduler)