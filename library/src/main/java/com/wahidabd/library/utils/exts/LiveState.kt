package com.wahidabd.library.utils.exts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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

@Composable
fun <T> StateFlow<Resource<T>>.collectStateFlow(
    onLoading: @Composable (() -> Unit),
    onEmpty: @Composable (() -> Unit)? = null,
    onFailure: @Composable (Throwable?, String?) -> Unit?,
    onSuccess: @Composable (data: T) -> Unit,
) {
    this.collectAsState().value.also {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading.invoke()
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
        }
    }
}

@Composable
fun <T> MutableStateFlow<Resource<T>>.collectStateFlow(
    onLoading: @Composable (() -> Unit),
    onEmpty: @Composable (() -> Unit)? = null,
    onFailure: @Composable (Throwable?, String?) -> Unit?,
    onSuccess: @Composable (data: T) -> Unit,
) {
    this.collectAsState().value.also {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading.invoke()
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
        }
    }
}