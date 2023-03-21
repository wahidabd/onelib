package com.wahidabd.library.base.coroutine

import com.wahidabd.library.data.model.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

class ErrorParses(private val retrofit: Retrofit) {

    fun convertGenericError(error: ResponseBody): ApiError? {
        val converter: Converter<ResponseBody, ApiError> = retrofit
            .responseBodyConverter(ApiError::class.java, arrayOfNulls(0))
        return converter.convert(error)
    }
}