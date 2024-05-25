package com.wahidabd.library.data


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

/**
 * A sealed class that represents the state of a resource.
 * It can be used to represent the different states of data in an application.
 *
 * @param T The type of data contained in the resource.
 */
sealed class Resource <T> {
    companion object {

        /**
         * Returns a [Resource.Default] instance.
         *
         * @return [Resource.Default]
         */
        fun <T> default(): Resource<T> = Default()

        /**
         * Returns a [Resource.Empty] instance.
         *
         * @return [Resource.Empty]
         */
        fun <T> empty(): Resource<T> = Empty()

        /**
         * Returns a [Resource.Failure] instance with the given error message.
         *
         * @param message The error message.
         * @return [Resource.Failure] containing the error message.
         */
        fun <T> fail(message: String?): Resource<T> =
            Failure(message = message)

        /**
         * Returns a [Resource.Loading] instance.
         *
         * @return [Resource.Loading]
         */
        fun <T> loading(): Resource<T> =
            Loading()

        /**
         * Returns a [Resource.Success] instance with the given data.
         *
         * @param data The data.
         * @return [Resource.Success] containing the data.
         */
        fun <T> success(data: T): Resource<T> =
            Success(data)
    }

    /**
     * Represents a failed resource with an error message.
     *
     * @param message The error message.
     * @param T The type of data that would have been contained in the resource.
     */
    data class Failure<T>(
        val message: String?
    ): Resource<T>()

    /**
     * Represents a successful resource with data.
     *
     * @param data The data.
     * @param T The type of data contained in the resource.
     */
    data class Success<T>(
        val data: T
    ): Resource<T>()

    /**
     * Represents a resource in its default state.
     *
     * @param T The type of data that would be contained in the resource.
     */
    class Default<T>: Resource<T>()

    /**
     * Represents a resource that is currently loading.
     *
     * @param T The type of data that would be contained in the resource.
     */
    class Loading<T>: Resource<T>()

    /**
     * Represents an empty resource.
     *
     * @param T The type of data that would be contained in the resource.
     */
    class Empty<T>: Resource<T>()
}