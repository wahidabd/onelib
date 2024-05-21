package com.wahidabd.onelibrary.domain.firebase.auth.model.mapper

import com.wahidabd.onelibrary.data.firebase.auth.model.FirebaseAuthRequest
import com.wahidabd.onelibrary.data.firebase.auth.model.FirebaseAuthResponse
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuth
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuthParam


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


fun FirebaseAuthParam.toRequest(): FirebaseAuthRequest {
    return FirebaseAuthRequest(
        email = email,
        password = password
    )
}

fun FirebaseAuthResponse.toDomain(): FirebaseAuth {
    return FirebaseAuth(
        uid = uid,
        email = email,
        name = name,
        phone = phone,
        photo = photo,
    )
}