package com.wahidabd.library.utils.ktorclient

import com.wahidabd.library.data.Resource
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.coroutine.handler.coroutineErrorHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay


/**
 * Created by Wahid on 9/30/2023.
 * Github github.com/wahidabd.
 */


suspend inline fun <reified T : Any, reified U : Any> invoke(
    client: HttpClient,
    request: HttpRequestBuilder,
    retryCount: Int = 3
): Resource<T> {
    return try {
        val res = if (retryCount > 0) {
            callWithRetry(client, request, retryCount)
        } else {
            client.request(request)
        }

        if (res.status.isSuccess()) {
            val body = res.body<T>()
            Resource.success(body)
        } else {
            when (val error = res.body<U>()) {
                is ApiError -> Resource.fail(error.message)
                else -> Resource.fail("UNKNOWN ERROR")
            }
        }
    } catch (e: Exception) {
        coroutineErrorHandler(e)
    }
}

suspend inline fun callWithRetry(
    client: HttpClient,
    request: HttpRequestBuilder,
    times: Int
): HttpResponse {
    var res = client.request(request)

    repeat(times) {
        if (res.status.value != HttpStatusCode.TooManyRequests.value) return res
        else {
            delay(1000)
            res = client.request(request)
        }
    }

    return res
}