package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Resource


/**
 * Created by wahid on 5/14/2024.
 * Github github.com/wahidabd.
 */


/**
 * Transforms the data within a `Resource` using the provided `transform` function and returns
 * a new `Resource` containing the transformed data.
 *
 * @param T The type of data contained in the original `Resource`.
 * @param R The type of data to be contained in the resulting `Resource`.
 * @param transform A function that takes a value of type `T` and returns a value of type `R`.
 *
 * @return A new `Resource<R>` containing the transformed data or maintaining the same state
 *         (e.g., loading, failure, empty, or default).
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


/**
 * Transforms each element within a list contained in a `Resource` using the provided `transform` function and returns
 * a new `Resource` containing a list of the transformed elements.
 *
 * @param T The type of elements in the original list.
 * @param R The type of elements in the resulting list.
 * @param transform A function that takes a value of type `T` and returns a value of type `R`.
 * @return A new `Resource<List<R>>` containing the transformed list or maintaining the same state
 *         (e.g., loading, failure, empty, or default).
 */
fun <T, R> Resource<List<T>>.oneMapList(transform: (T) -> R): Resource<List<R>> {
    return when (this) {
        is Resource.Success -> Resource.success(data.map { transform(it) })
        is Resource.Failure -> Resource.fail(message)
        is Resource.Loading -> Resource.loading()
        is Resource.Default -> Resource.default()
        is Resource.Empty -> Resource.empty()
    }
}