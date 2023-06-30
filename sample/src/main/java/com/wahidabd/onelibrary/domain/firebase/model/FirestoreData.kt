package com.wahidabd.onelibrary.domain.firebase.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.onelibrary.data.firebase.model.FirestoreResponse


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


data class FirestoreData(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val age: Int? = 0,
    val address: String? = emptyString()
)

fun FirestoreResponse.toDomain(): FirestoreData =
    FirestoreData(
        id, name, age, address
    )