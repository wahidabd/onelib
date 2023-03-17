package com.wahidabd.library.data.libs

import com.google.gson.Gson
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.exception.ApiException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
object UnsuccessfulResponseHandler {

    val gson = Gson()

    fun <T> getApiError(errorResponse: String?, response: Response<T>): ApiException {
        val defaultApiError = ApiError(406, "Invalid Response", "Not Acceptable")
        return if (errorResponse != null){
            lateinit var apiException: ApiException
            if (!isValidJsonObject(errorResponse) &&  !isValidJsonArray(errorResponse)){
                apiException = ApiException(defaultApiError, response)
            }

            apiException
        }else{
            ApiException(defaultApiError, response)
        }
    }

    fun <T> parseCustomApiErrorResponse(errorResponse: String, clazz: Class<T>): T =
        gson.fromJson(errorResponse, clazz)

    private fun isValidJsonObject(errorResponse: String): Boolean =
        try {
            JSONObject(errorResponse)
            true
        }catch (e: JSONException){
            e.printStackTrace()
            false
        }

    private fun isValidJsonArray(errorResponse: String): Boolean =
        try {
            JSONArray(errorResponse)
            true
        }catch (e: JSONException){
            e.printStackTrace()
            false
        }

}