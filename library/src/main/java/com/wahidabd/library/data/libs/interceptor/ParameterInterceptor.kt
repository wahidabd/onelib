package com.wahidabd.library.data.libs.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * ParameterInterceptor is a custom OkHttp Interceptor designed to add query parameters to
 * all outgoing HTTP requests. This can be particularly useful for appending common parameters
 * such as API keys, authentication tokens, or other required query parameters to every request.
 *
 * @property params A HashMap containing the query parameters to be added to each request.
 */
class ParameterInterceptor(
    private val params: HashMap<String, String>
) : Interceptor {


    /**
     * Intercepts the outgoing HTTP request, adds the specified query parameters, and proceeds with the request.
     *
     * @param chain The Interceptor.Chain object which provides the request to be intercepted.
     * @return The response obtained after proceeding with the modified request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .url(mapParameters(chain))

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    /**
     * Maps the specified query parameters to the original request URL.
     *
     * @param chain The Interceptor.Chain object which provides the request containing the original URL.
     * @return A new HttpUrl object with the added query parameters.
     */
    private fun mapParameters(chain: Interceptor.Chain): HttpUrl {
        val original = chain.request()
        val originalHttpUrl = original.url

        val builder = originalHttpUrl.newBuilder()

        for ((key, value) in params){
            builder.addQueryParameter(key, value)
        }

        return builder.build()
    }
}