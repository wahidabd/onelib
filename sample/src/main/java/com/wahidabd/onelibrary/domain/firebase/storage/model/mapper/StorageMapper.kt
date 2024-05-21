package com.wahidabd.onelibrary.domain.firebase.storage.model.mapper

import com.wahidabd.onelibrary.data.firebase.storage.model.StorageRequest
import com.wahidabd.onelibrary.data.firebase.storage.model.StorageResponse
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageData
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageParam


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


fun StorageResponse.toDomain(): StorageData {
    return StorageData(
        id = id,
        name = name,
        url = url
    )
}

fun StorageParam.toReq(): StorageRequest {
    return StorageRequest(
        name = name,
        file = file
    )
}