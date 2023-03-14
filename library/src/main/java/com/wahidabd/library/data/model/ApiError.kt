package com.wahidabd.library.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
data class ApiError(
    @SerializedName("status")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val errorCode: String
)
