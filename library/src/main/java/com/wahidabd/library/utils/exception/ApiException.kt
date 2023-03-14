package com.wahidabd.library.utils.exception

import com.wahidabd.library.data.model.ApiError
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
class ApiException(apiError: ApiError, response: Response<*>) : ResponseException(response) {
    val apiError = apiError
    override var response = response
}