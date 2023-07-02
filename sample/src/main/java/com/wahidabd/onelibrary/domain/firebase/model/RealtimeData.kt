package com.wahidabd.onelibrary.domain.firebase.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeResponse


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeData(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val age: Int? = 0
)

fun RealtimeResponse.toDomain(): RealtimeData =
    RealtimeData(id, name, age)