package com.wahidabd.library.utils.coroutine.handler

import com.wahidabd.library.data.model.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

/**
 * A utility class for parsing error responses using Retrofit.
 *
 * @param retrofit The Retrofit instance used to create the converter for parsing errors.
 */
class ErrorParser(private val retrofit: Retrofit) {

    /**
     * Converts a generic error response body into an `ApiError` object.
     *
     * @param error The error response body to be converted.
     * @return An `ApiError` object if the conversion is successful, or null if the conversion fails.
     */
    fun convertGenericError(error: ResponseBody): ApiError? {
        val converter: Converter<ResponseBody, ApiError> = retrofit
            .responseBodyConverter(ApiError::class.java, arrayOfNulls(0))
        return converter.convert(error)
    }
}