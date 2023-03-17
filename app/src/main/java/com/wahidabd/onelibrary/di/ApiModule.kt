package com.wahidabd.onelibrary.di

import com.wahidabd.library.data.libs.OkHttpClientFactory
import com.wahidabd.library.data.libs.interceptor.HeaderInterceptor
import com.wahidabd.library.data.libs.interceptor.ParameterInterceptor
import com.wahidabd.onelibrary.BuildConfig
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL: String = ""

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            interceptors = listOf(getParameterInterceptor(), getHeaderInterceptor()),
            showDebugLog = BuildConfig.DEBUG,
            certificatePinner = null,
            authenticator = null
        )
    }

    single(named(BASE_URL)) {
        BuildConfig.BASE_URL
    }

}


private fun getParameterInterceptor(): Interceptor {
    val params = HashMap<String, String>()
    params["api_key"] = BuildConfig.API_KEY
    return ParameterInterceptor(params)
}

private fun getHeaderInterceptor(): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers)
}