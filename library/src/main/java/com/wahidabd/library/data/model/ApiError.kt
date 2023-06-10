package com.wahidabd.library.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
data class ApiError(
    @SerializedName("status")
    val statusCode: Int,
    @SerializedName(
        value = "message",
        alternate = ["error", "error_message", "status_message", "error_status","errorMessage"]
    )
    val message: String,
    @SerializedName(value = "code", alternate = ["error_code", "errorCode"])
    val errorCode: String
)
