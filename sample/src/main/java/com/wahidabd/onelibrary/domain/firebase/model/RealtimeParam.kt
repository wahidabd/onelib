package com.wahidabd.onelibrary.domain.firebase.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import java.io.File


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeParam(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val age: Int? = 0,
    val file: File? = null
)

fun RealtimeParam.toRequest(): RealtimeRequest =
    RealtimeRequest(
        name = name,
        age = age,
        file = file
    )
