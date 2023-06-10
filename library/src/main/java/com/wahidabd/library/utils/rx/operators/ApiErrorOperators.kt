package com.wahidabd.library.utils.rx.operators

import com.wahidabd.library.data.libs.UnsuccessfulResponseHandler
import com.wahidabd.library.data.model.ApiError
import com.wahidabd.library.utils.exception.ApiException
import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */


fun <T : Any> getObservableApiError(): ObservableApiErrorOperator<T, ApiError> =
    ObservableApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = {it.toApiError()}
    )

fun <T: Any> getSingleApiError(): SingleApiErrorOperator<T, ApiError> =
    SingleApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = {it.toApiError()}
    )

fun <T: Any> getFlowableApiError(): FlowableApiErrorOperator<T, ApiError> =
    FlowableApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = {it.toApiError()}
    )

fun ApiError.toApiError(): ApiError =
    ApiError(statusCode, message, errorCode)


fun <T, R> handleErrorResponseToApiException(
    response: Response<T>,
    errorClazz: Class<R>?,
    mabObject: ((R) -> ApiError)?,
) : ApiException {
    val errorBody = response.errorBody()
    val errorResponse = errorBody?.toString()

    val apiException: ApiException = if (errorClazz == null && mabObject == null){
        UnsuccessfulResponseHandler.INSTANCE.getApiError(errorResponse.toString(), response)
    }else{
        if (errorClazz == null){
            throw IllegalArgumentException("errorClazz can not be null")
        }

        if (mabObject == null){
            throw IllegalArgumentException("mapObject can not be null")
        }

        val parsing = UnsuccessfulResponseHandler.INSTANCE.parseCustomApiErrorResponse(errorResponse.toString(), errorClazz)
        ApiException(mabObject.invoke(parsing), response)
    }

    return apiException
}