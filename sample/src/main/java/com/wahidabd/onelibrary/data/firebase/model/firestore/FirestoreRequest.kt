package com.wahidabd.onelibrary.data.firebase.model.firestore

import com.google.firebase.firestore.Exclude
import com.wahidabd.library.utils.common.emptyString
import java.io.File


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


data class FirestoreRequest(
    var id: String? = emptyString(),
    var image: String? = emptyString(),
    val name: String,
    val age: Int,
    val address: String,
    val file: File? = null
){
    @Exclude
    fun toMap() = hashMapOf<String, Any?>(
        "id" to id,
        "name" to name,
        "age" to age,
        "address" to address,
        "image" to image
    )
}
