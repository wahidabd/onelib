package com.wahidabd.library.data.libs.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ParameterInterceptor(
    private val params: HashMap<String, String>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .url(mapParameters(chain))

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

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