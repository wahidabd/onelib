package com.wahidabd.library.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


/**
 * Extension function to collect StateFlow values and handle different Resource states.
 *
 * @param T The type of the data being observed.
 * @param onLoading A composable function to be invoked when the Resource is in a loading state.
 * @param onEmpty A composable function to be invoked when the Resource is empty. This parameter is optional.
 * @param onFailure A composable function to be invoked when the Resource fails. Takes a nullable string message.
 * @param onSuccess A composable function to be invoked when the Resource is successful. Takes the data of type T.
 */
@Composable
fun <T> StateFlow<Resource<T>>.collectStateFlow(
    onLoading: @Composable (() -> Unit),
    onEmpty: @Composable (() -> Unit)? = null,
    onFailure: @Composable (String?) -> Unit?,
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
                onFailure.invoke(it.message)
            }

            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
        }
    }
}

/**
 * Extension function to collect MutableStateFlow values and handle different Resource states.
 *
 * @param T The type of the data being observed.
 * @param onLoading A composable function to be invoked when the Resource is in a loading state.
 * @param onEmpty A composable function to be invoked when the Resource is empty. This parameter is optional.
 * @param onFailure A composable function to be invoked when the Resource fails. Takes a nullable string message.
 * @param onSuccess A composable function to be invoked when the Resource is successful. Takes the data of type T.
 */
@Composable
fun <T> MutableStateFlow<Resource<T>>.collectStateFlow(
    onLoading: @Composable (() -> Unit),
    onEmpty: @Composable (() -> Unit)? = null,
    onFailure: @Composable (String?) -> Unit?,
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
                onFailure.invoke(it.message)
            }

            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
        }
    }
}