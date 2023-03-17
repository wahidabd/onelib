package com.wahidabd.library.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
class ApiResponse<T> {

    @SerializedName("code")
    val code: String? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("data")
    val data: Object? = null

}