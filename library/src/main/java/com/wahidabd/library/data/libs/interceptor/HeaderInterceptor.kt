package com.wahidabd.library.data.libs.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class HeaderInterceptor(
    private val headers: HashMap<String, String>
) : Interceptor{

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = mapHeaders(chain)
        return chain.proceed(request)
    }

    private fun mapHeaders(chain: Interceptor.Chain): Request {
        val original =  chain.request()
        val requestBuilder = original.newBuilder()

        for ((key, value) in headers){
            requestBuilder.addHeader(key, value)
        }
        return requestBuilder.build()
    }
}