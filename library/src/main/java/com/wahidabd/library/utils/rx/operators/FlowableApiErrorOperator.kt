package com.wahidabd.library.utils.rx.operators

import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.exception.ApiException
import io.reactivex.rxjava3.core.FlowableOperator
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
class FlowableApiErrorOperator<T : Any, R>(
    private var errorClazz: Class<R>? = null,
    private var mapObject: ((R) -> ApiError)? = null
) : FlowableOperator<T, Response<T>> {
    override fun apply(subscriber: Subscriber<in T>): Subscriber<in Response<T>> =
        object : Subscriber<Response<T>> {
            override fun onNext(t: Response<T>?) {
                if (t?.isSuccessful == false) {
                    subscriber.onError(handleErrorResponseToApiException(t, errorClazz, mapObject))
                } else if (t?.code() == 204) {
                    val apiError = ApiError(204, "Data Empty", "No Content")
                    subscriber.onError(ApiException(apiError, t))
                } else {
                    t?.body()?.let { subscriber.onComplete() }
                }
            }

            override fun onSubscribe(s: Subscription?) {
                subscriber.onSubscribe(s)
            }

            override fun onError(t: Throwable?) {
                subscriber.onError(t)
            }

            override fun onComplete() {
                subscriber.onComplete()
            }

        }

}