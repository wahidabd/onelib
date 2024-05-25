package com.wahidabd.library.data.libs

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


/**
 * OkHttpClientFactory is a utility object for creating instances of OkHttpClient with
 * custom configurations. It allows the inclusion of interceptors, an authenticator,
 * certificate pinning, and logging for debugging purposes.
 */
object OkHttpClientFactory {

    // Default maximum number of requests allowed
    private const val DEFAULT_MAX_REQUEST = 30

    /**
     * Creates an instance of OkHttpClient with the specified configurations.
     *
     * @param interceptors A list of interceptors to be added to the OkHttpClient instance.
     * @param authenticator An optional Authenticator for handling authentication challenges.
     * @param certificatePinner An optional CertificatePinner for securing HTTPS connections with certificate pinning.
     * @param showDebugLog A boolean flag indicating whether to enable HTTP request/response logging for debugging.
     * @return An instance of OkHttpClient configured with the specified parameters.
     */
    fun create(
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
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(interceptor).build()
        }

        val dispatcher = Dispatcher().apply {
            maxRequests = DEFAULT_MAX_REQUEST
        }

        okHttpClient.dispatcher(dispatcher)
        return okHttpClient.build()
    }

}