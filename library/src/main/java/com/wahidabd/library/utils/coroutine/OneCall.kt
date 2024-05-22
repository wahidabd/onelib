package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Resource
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.coroutine.handler.coroutineErrorHandler
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

/**
 * A utility object for making network calls and handling their responses.
 */
object OneCall {

    /**
     * Executes a network call and handles its response.
     *
     * @param T The type of the response body.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the following steps:
     * - Executes the network call.
     * - If the response is successful and the body is not null, emits a successful Resource.
     * - If the response headers are empty, emits an empty Resource.
     * - If the error body is not null, converts it to an ApiError and emits a failure Resource with the error message.
     * - If an unknown error occurs, emits a failure Resource with a generic error message.
     * - If an exception occurs during the call, handles the exception and emits a corresponding Resource.
     */
    suspend fun <T> enqueue(
        converter: (ResponseBody) -> ApiError?,
        call: suspend () -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call()
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (response.headers().size == 0) {
                onEmit.invoke(Resource.empty())
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }

    /**
     * Executes a network call with one request parameter and handles its response.
     *
     * @param T The type of the response body.
     * @param R The type of the request parameter.
     * @param req The request parameter.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call with one parameter.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the same steps as the no-parameter version of enqueue.
     */
    suspend fun <T, R> enqueue(
        req: R,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (R) -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call(req)
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }


    /**
     * Executes a network call with two request parameters and handles its response.
     *
     * @param T The type of the response body.
     * @param R The type of the first request parameter.
     * @param S The type of the second request parameter.
     * @param req1 The first request parameter.
     * @param req2 The second request parameter.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call with two parameters.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the same steps as the no-parameter version of enqueue.
     */
    suspend fun <T, R, S> enqueue(
        req1: R,
        req2: S,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (R, S) -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call(req1, req2)
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }


    /**
     * Executes a network call with three request parameters and handles its response.
     *
     * @param T The type of the response body.
     * @param R The type of the first request parameter.
     * @param S The type of the second request parameter.
     * @param U The type of the third request parameter.
     * @param req1 The first request parameter.
     * @param req2 The second request parameter.
     * @param req3 The third request parameter.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call with three parameters.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the same steps as the no-parameter version of enqueue.
     */
    suspend fun <T, R, S, U> enqueue(
        req1: R,
        req2: S,
        req3: U,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (R, S, U) -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call(req1, req2, req3)
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }


    /**
     * Executes a network call with four request parameters and handles its response.
     *
     * @param T The type of the response body.
     * @param R The type of the first request parameter.
     * @param S The type of the second request parameter.
     * @param U The type of the third request parameter.
     * @param V The type of the fourth request parameter.
     * @param req1 The first request parameter.
     * @param req2 The second request parameter.
     * @param req3 The third request parameter.
     * @param req4 The fourth request parameter.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call with four parameters.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the same steps as the no-parameter version of enqueue.
     */
    suspend fun <T, R, S, U, V> enqueue(
        req1: R,
        req2: S,
        req3: U,
        req4: V,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (R, S, U, V) -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call(req1, req2, req3, req4)
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }

    /**
     * Executes a network call with four request parameters and handles its response.
     *
     * @param T The type of the response body.
     * @param R The type of the first request parameter.
     * @param S The type of the second request parameter.
     * @param U The type of the third request parameter.
     * @param V The type of the fourth request parameter.
     * @param W The type of the fifth request parameter.
     * @param req1 The first request parameter.
     * @param req2 The second request parameter.
     * @param req3 The third request parameter.
     * @param req4 The fourth request parameter.
     * @param req5 The fifth request parameter.
     * @param converter A function to convert the error body to an ApiError.
     * @param call A suspend function representing the network call with four parameters.
     * @param onEmit A suspend function to handle the emitted Resource based on the call result.
     *
     * This function performs the same steps as the no-parameter version of enqueue.
     */
    suspend fun <T, R, S, U, V, W> enqueue(
        req1: R,
        req2: S,
        req3: U,
        req4: V,
        req5: W,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (R, S, U, V, W) -> Response<T>,
        onEmit: suspend ((Resource<T>) -> Unit)
    ) {
        try {
            val response = call(req1, req2, req3, req4, req5)
            val body = response.body()
            val errBody = response.errorBody()

            if (response.isSuccessful && body != null) {
                onEmit.invoke(Resource.success(body))
            } else if (errBody != null) {
                val parsedError = converter(errBody)
                if (parsedError != null) {
                    onEmit.invoke(Resource.fail(parsedError.message))
                } else {
                    onEmit.invoke(Resource.fail("An unknown error occurred"))
                }
            } else {
                onEmit.invoke(Resource.fail("An unknown error occurred"))
            }
        } catch (e: Exception) {
            onEmit.invoke(coroutineErrorHandler(e))
        }
    }
}
