package com.wahidabd.onelibrary.data.firebase.auth.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


data class FirebaseAuthResponse(
    val uid: String? = emptyString(),
    val name: String? = emptyString(),
    val email: String? = emptyString(),
    val phone: String? = emptyString(),
    val photo: String? = emptyString()
)
