package com.wahidabd.library.utils.rx.operators

import io.reactivex.rxjava3.core.FlowableOperator
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
class FlowableApiErrorOperator<T : Any, R> : FlowableOperator<T, Response<T>> {
    override fun apply(subscriber: Subscriber<in T>): Subscriber<in Response<T>> {
        TODO("Not yet implemented")
    }

}