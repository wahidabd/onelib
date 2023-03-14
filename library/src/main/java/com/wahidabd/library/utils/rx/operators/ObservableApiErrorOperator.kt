package com.wahidabd.library.utils.rx.operators

import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.exception.ApiException
import io.reactivex.rxjava3.core.ObservableOperator
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

class ObservableApiErrorOperator<T : Any, R>(
    var errorClazz: Class<R>?,
    var mapObject: ((R) -> ApiError)? = null
) : ObservableOperator<T, Response<T>> {


    override fun apply(observer: Observer<in T>): Observer<in Response<T>> =
        object : Observer<Response<T>>, DisposableSingleObserver<T>() {

            override fun onSuccess(t: T) {
                observer.onComplete()
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onComplete() {
                observer.onComplete()
            }

            override fun onNext(t: Response<T>) {
                if (!t.isSuccessful){
                    observer.onError(handleErrorResponseToApiException(t, errorClazz, mapObject))
                }else if (t.code() == 204){
                    val apiError = ApiError(204, "Data Empty", "No Content")
                    observer.onError(ApiException(apiError, t))
                }else{
                    observer.onComplete()
                }
            }

        }

}