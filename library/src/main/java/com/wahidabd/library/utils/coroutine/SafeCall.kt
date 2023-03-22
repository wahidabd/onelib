package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Result
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.rx.apihandlers.coroutineErrorHandler
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

class SafeCall {

    // call without parameter
    suspend fun <T> enqueue(
        converter: (ResponseBody) -> ApiError?,
        call: suspend () -> Response<T>
    ): Result<T?> = try {
        val response = call()
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 1 parameter
    suspend fun <T, U> enqueue(
        req: U,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U) -> Response<T>
    ): Result<T?> = try {
        val response = call(req)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 2 parameters
    suspend fun <T, U, R> enqueue(
        req1: U, req2: R,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U, R) -> Response<T>
    ): Result<T?> = try {
        val response = call(req1, req2)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 2 parameters
    suspend fun <T, U, R, S> enqueue(
        req1: U, req2: R, req3: S,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U, R, S) -> Response<T>
    ): Result<T?> = try {
        val response = call(req1, req2, req3)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 3 parameters
    suspend fun <T, U, R, S, V> enqueue(
        req1: U, req2: R, req3: S, req4: V,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U, R, S, V) -> Response<T>
    ): Result<T?> = try {
        val response = call(req1, req2, req3, req4)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 4 parameters
    suspend fun <T, U, R, S, V, W> enqueue(
        req1: U, req2: R, req3: S, req4: V, req5: W,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U, R, S, V, W) -> Response<T>
    ): Result<T?> = try {
        val response = call(req1, req2, req3, req4, req5)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

    // call with 2 parameters
    suspend fun <T, U, R, S, V, W, X> enqueue(
        req1: U, req2: R, req3: S, req4: V, req5: W, req6: X,
        converter: (ResponseBody) -> ApiError?,
        call: suspend (U, R, S, V, W, X) -> Response<T>
    ): Result<T?> = try {
        val response = call(req1, req2, req3, req4, req5, req6)
        val body = response.body()
        val errBody = response.errorBody()

        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else if (errBody != null) {
            val parsedError = converter(errBody)
            if (parsedError != null) {
                Result.fail(parsedError.message)
            } else {
                Result.fail("An unknown error occurred")
            }
        } else {
            Result.fail("An unknown error occurred")
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }

}