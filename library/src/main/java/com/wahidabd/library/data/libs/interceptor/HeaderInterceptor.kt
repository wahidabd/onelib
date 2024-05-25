package com.wahidabd.library.data.libs.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException


/**
 * HeaderInterceptor is a custom OkHttp Interceptor that allows adding headers to all HTTP requests.
 *
 * This interceptor is particularly useful when you need to include common headers (such as
 * authorization tokens, content types, etc.) across multiple HTTP requests made by an OkHttp client.
 *
 * @property headers A HashMap containing key-value pairs of headers to be added to the requests.
 *
 * Example usage:
 * ```
 * val headers = hashMapOf(
 *     "Authorization" to "Bearer your_token",
 *     "Content-Type" to "application/json"
 * )
 * val headerInterceptor = HeaderInterceptor(headers)
 * val okHttpClient = OkHttpClient.Builder()
 *     .addInterceptor(headerInterceptor)
 *     .build()
 * ```
 */
class HeaderInterceptor(
    private val headers: HashMap<String, String>
) : Interceptor{


    /**
     * Intercepts the HTTP request, adds the specified headers, and proceeds with the request.
     *
     * @param chain The interceptor chain that allows the request to proceed with the added headers.
     * @return The HTTP response after the headers have been added to the request.
     * @throws IOException If an I/O error occurs during the request.
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = mapHeaders(chain)
        return chain.proceed(request)
    }

    /**
     * Maps the headers stored in the interceptor to the HTTP request.
     *
     * This function takes the original request, adds the headers from the `headers` property,
     * and returns a new request with these headers.
     *
     * @param chain The interceptor chain that contains the original request.
     * @return A new request with the added headers.
     */
    private fun mapHeaders(chain: Interceptor.Chain): Request {
        val original =  chain.request()
        val requestBuilder = original.newBuilder()

        for ((key, value) in headers){
            requestBuilder.addHeader(key, value)
        }
        return requestBuilder.build()
    }
}