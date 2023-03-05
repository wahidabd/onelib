package com.wahidabd.library.data.libs

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    fun <T> createReactiveService(
        serviceClass: Class<T>,
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): T {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(serviceClass)
    }

    fun <T> createReactiveService(
        serviceClass: Class<T>,
        okHttpClient: OkHttpClient,
        baseUrl: String,
        factory: Converter.Factory
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(factory)
            .build()

        return retrofit.create(serviceClass)
    }

    fun <T> createService(serviceClass: Class<T>, okHttpClient: OkHttpClient, baseUrl: String): T {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(serviceClass)
    }

}