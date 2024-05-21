package com.wahidabd.onelibrary.domain.firebase.firestore.model

import com.wahidabd.onelibrary.data.firebase.firestore.model.FirestoreRequest


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


data class FirestoreParam(
    val name: String,
    val age: Int,
    val address: String,
)

fun FirestoreParam.toRequest(): FirestoreRequest =
    FirestoreRequest(
        name = name,
        age = age,
        address = address,
    )
