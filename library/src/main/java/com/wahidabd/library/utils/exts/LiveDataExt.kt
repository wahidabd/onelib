package com.wahidabd.library.utils.exts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.wahidabd.library.data.Resource

fun <T> LiveData<Resource<T>>.observerLiveData(
    owner: LifecycleOwner,
    onLoading: (() -> Unit)?,
    onSuccess: (T) -> Unit,
    onEmpty: (() -> Unit)? = null,
    onFailure: (Throwable?, String?) -> Unit
) {
    this.observe(owner) {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading?.invoke()
            }
            is Resource.Failure -> {
                onFailure.invoke(it.throwable, it.message)
            }
            is Resource.Empty -> {
                onEmpty?.invoke()
            }
            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
        }
    }

}

@Composable
fun <T> LiveData<Resource<T>>.observerLiveDataState(
    onLoading: @Composable (() -> Unit)?,
    onSuccess: @Composable (T) -> Unit,
    onEmpty: @Composable (() -> Unit)?,
    onFailure: @Composable (Throwable?, String?) -> Unit
) {
    this.observeAsState().value.let {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading?.invoke()
            }
            is Resource.Empty -> {
                onEmpty?.invoke()
            }
            is Resource.Failure -> {
                onFailure.invoke(it.throwable, it.message)
            }
            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
            else -> {}
        }
    }

}