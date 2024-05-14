package com.wahidabd.library.data


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

sealed class Resource <T> {
    companion object {

        fun <T> default(): Resource<T> = Default()

        fun <T> empty(): Resource<T> = Empty()

        fun <T> fail(message: String?): Resource<T> =
            Failure(message = message)

        fun <T> loading(): Resource<T> =
            Loading()

        fun <T> success(data: T): Resource<T> =
            Success(data)
    }

    data class Failure<T>(
        val message: String?
    ): Resource<T>()

    data class Success<T>(
        val data: T
    ): Resource<T>()

    class Default<T>: Resource<T>()
    class Loading<T>: Resource<T>()
    class Empty<T>: Resource<T>()
}