package com.wahidabd.library.data.libs

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiService is an object that provides utility methods to create Retrofit service instances.
 * It supports creating both reactive and non-reactive services with customizable configurations.
 *
 * The available methods in this object allow you to:
 * - Create a reactive Retrofit service with a default Gson converter.
 * - Create a reactive Retrofit service with a custom converter factory.
 * - Create a non-reactive Retrofit service with a default Gson converter.
 * - Create a base Retrofit instance with a default Gson converter.
 */
object ApiService {

    /**
     * Creates a reactive Retrofit service with a default Gson converter.
     *
     * @param T The service interface type.
     * @param serviceClass The class of the service interface.
     * @param okHttpClient An OkHttpClient instance for making HTTP requests.
     * @param baseUrl The base URL for the HTTP requests.
     * @return An implementation of the service interface.
     */
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

    /**
     * Creates a reactive Retrofit service with a custom converter factory.
     *
     * @param T The service interface type.
     * @param serviceClass The class of the service interface.
     * @param okHttpClient An OkHttpClient instance for making HTTP requests.
     * @param baseUrl The base URL for the HTTP requests.
     * @param factory A custom converter factory for serialization and deserialization.
     * @return An implementation of the service interface.
     */
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


    /**
     * Creates a non-reactive Retrofit service with a default Gson converter.
     *
     * @param T The service interface type.
     * @param serviceClass The class of the service interface.
     * @param okHttpClient An OkHttpClient instance for making HTTP requests.
     * @param baseUrl The base URL for the HTTP requests.
     * @return An implementation of the service interface.
     */
    fun <T> createService(serviceClass: Class<T>, okHttpClient: OkHttpClient, baseUrl: String): T {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(serviceClass)
    }


    /**
     * Creates a base Retrofit instance with a default Gson converter.
     *
     * @param okHttpClient An OkHttpClient instance for making HTTP requests.
     * @param baseUrl The base URL for the HTTP requests.
     * @return A configured Retrofit instance.
     */
    fun createService(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}