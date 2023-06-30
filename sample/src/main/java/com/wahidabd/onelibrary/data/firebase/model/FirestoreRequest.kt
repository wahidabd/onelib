package com.wahidabd.onelibrary.data.firebase.model

import com.google.firebase.firestore.Exclude
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


data class FirestoreRequest(
    var id: String? = emptyString(),
    val name: String,
    val age: Int,
    val address: String
){
    @Exclude
    fun toMap() = mutableMapOf(
        "id" to id,
        "name" to name,
        "age" to age,
        "address" to address
    )
}
