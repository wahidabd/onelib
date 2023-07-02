package com.wahidabd.onelibrary.domain.firebase.model

import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


data class FirestoreParam(
    val name: String,
    val age: Int,
    val address: String
)

fun FirestoreParam.toRequest(): FirestoreRequest =
    FirestoreRequest(
        name = name,
        age = age,
        address = address
    )
