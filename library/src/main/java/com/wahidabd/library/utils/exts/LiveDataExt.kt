package com.wahidabd.library.utils.exts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.wahidabd.library.data.Result

fun <T> LiveData<Result<T>>.observerLiveData(
    owner: LifecycleOwner,
    onLoading: (() -> Unit)?,
    onSuccess: (T) -> Unit,
    onEmpty: (() -> Unit)?,
    onFailure: (Throwable?, String?) -> Unit
){
    this.observe(owner) {
        when(it){
            is Result.Loading -> {
                onLoading?.invoke()
            }
            is Result.Success -> {
                onSuccess.invoke(it.data)
            }
            is Result.Empty -> {
                onEmpty?.invoke()
            }
            is Result.Failure -> {
                onFailure.invoke(it.throwable, it.message)
            }
            is Result.Default -> {}
        }
    }

}