package com.wahidabd.library.utils.exts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.wahidabd.library.data.Resource


/**
 * Observes a LiveData of type [Resource] and handles different states: loading, success, empty, and failure.
 *
 * @param owner The [LifecycleOwner] which controls the observer.
 * @param onLoading Optional lambda invoked when the [Resource] is in the loading state.
 * @param onSuccess Lambda invoked when the [Resource] is in the success state with the non-null data.
 * @param onEmpty Optional lambda invoked when the [Resource] is in the empty state.
 * @param onFailure Lambda invoked when the [Resource] is in the failure state with the error message.
 */
fun <T> LiveData<Resource<T>>.observerLiveData(
    owner: LifecycleOwner,
    onLoading: (() -> Unit)?,
    onSuccess: (T) -> Unit,
    onEmpty: (() -> Unit)? = null,
    onFailure: (String?) -> Unit
) {
    this.observe(owner) {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading?.invoke()
            }

            is Resource.Failure -> {
                onFailure.invoke(it.message)
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

/**
 * Observes a LiveData of type [Resource] in a Composable function and handles different states: loading, success, empty, and failure.
 *
 * @param onLoading Optional composable lambda invoked when the [Resource] is in the loading state.
 * @param onSuccess Composable lambda invoked when the [Resource] is in the success state with the non-null data.
 * @param onEmpty Optional composable lambda invoked when the [Resource] is in the empty state.
 * @param onFailure Composable lambda invoked when the [Resource] is in the failure state with the error message.
 */
@Composable
fun <T> LiveData<Resource<T>>.observerLiveDataState(
    onLoading: @Composable (() -> Unit)?,
    onSuccess: @Composable (T) -> Unit,
    onEmpty: @Composable (() -> Unit)?,
    onFailure: @Composable (String?) -> Unit
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
                onFailure.invoke(it.message)
            }

            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }

            else -> {}
        }
    }
}