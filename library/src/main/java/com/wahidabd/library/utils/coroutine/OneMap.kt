package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Resource


/**
 * Created by wahid on 5/14/2024.
 * Github github.com/wahidabd.
 */


fun <T, R> Resource<T>.oneMap(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> Resource.success(transform(data))
        is Resource.Failure -> Resource.fail(message)
        is Resource.Loading -> Resource.loading()
        is Resource.Default -> Resource.default()
        is Resource.Empty -> Resource.empty()
    }
}