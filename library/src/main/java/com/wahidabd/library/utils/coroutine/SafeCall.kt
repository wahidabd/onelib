package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Resource
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.fallback
import com.wahidabd.library.utils.rx.apihandlers.coroutineErrorHandler
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
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
        }else if (errBody != null) {
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


suspend fun <T, R, S,U> enqueue(
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
