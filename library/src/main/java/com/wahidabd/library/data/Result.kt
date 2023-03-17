package com.wahidabd.library.data


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

sealed class Result <T> {
    companion object {

        fun <T> default(): Result<T> = Default()

        fun <T> empty(): Result<T> = Empty()

        fun <T> fail(message: String?): Result<T> =
            Failure(throwable = null, message = message)

        fun <T> fail(throwable: Throwable, message: String?): Result<T> =
            Failure(throwable = throwable, message = message)

        fun <T> loading(): Result<T> =
            Loading()

        fun <T> success(data: T): Result<T> =
            Success(data)
    }

    data class Failure<T>(
        val throwable: Throwable?,
        val message: String?
    ): Result<T>()

    data class Success<T>(
        val data: T
    ): Result<T>()

    class Default<T>: Result<T>()
    class Loading<T>: Result<T>()
    class Empty<T>: Result<T>()
}