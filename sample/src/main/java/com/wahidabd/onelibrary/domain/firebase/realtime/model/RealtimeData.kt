package com.wahidabd.onelibrary.domain.firebase.realtime.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.onelibrary.data.firebase.raltime.model.RealtimeResponse


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeData(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val age: Int? = 0,
    val image: String? = emptyString()
)

fun RealtimeResponse.toDomain(): RealtimeData =
    RealtimeData(id, name, age, image)
