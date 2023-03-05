package com.wahidabd.library.data.libs

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    private const val DEFAULT_MAX_REQUEST = 30

    fun onCreate(
        interceptors: List<Interceptor>,
        authenticator: Authenticator?,
        certificatePinner: CertificatePinner?,
        showDebugLog: Boolean
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(DEFAULT_MAX_REQUEST.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_MAX_REQUEST.toLong(), TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_MAX_REQUEST.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        if (certificatePinner != null) okHttpClient.certificatePinner(certificatePinner)
        if (authenticator != null) okHttpClient.authenticator(authenticator)

        for (i in interceptors){
            okHttpClient.addInterceptor(i)
        }

        if (showDebugLog){
            val interceptor = HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            }
            okHttpClient.addInterceptor(interceptor).build()
        }

        val dispatcher = Dispatcher().apply {
            maxRequests = DEFAULT_MAX_REQUEST
        }

        okHttpClient.dispatcher(dispatcher)
        return okHttpClient.build()
    }

}