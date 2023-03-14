package com.wahidabd.library.utils.rx.operators

import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.exception.ApiException
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.MaybeOperator
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Response

/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class MaybeApiErrorOperator<T: Any, R>(
    private var errorClazz: Class<R>? = null,
    private var mapObject: ((R) -> ApiError)? = null
) : MaybeOperator<T, Response<T>> {

    override fun apply(observer: MaybeObserver<in T>): MaybeObserver<in Response<T>> =
        object : MaybeObserver<Response<T>> {

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSuccess(t: Response<T>) {
                if (!t.isSuccessful){
                    observer.onError(handleErrorResponseToApiException(t, errorClazz, mapObject))
                }else if (t.code() == 204){
                    val apiError = ApiError(204, "Data Empty", "No Content")
                    observer.onError(ApiException(apiError, t))
                }else{
                    t.body()?.let { observer.onSuccess(it) }
                }
            }

        }

}