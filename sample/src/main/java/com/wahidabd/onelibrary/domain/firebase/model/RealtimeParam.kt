package com.wahidabd.onelibrary.domain.firebase.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeParam(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val age: Int? = 0
)

fun RealtimeParam.toRequest(): RealtimeRequest =
    RealtimeRequest(
        id, name, age
    )
