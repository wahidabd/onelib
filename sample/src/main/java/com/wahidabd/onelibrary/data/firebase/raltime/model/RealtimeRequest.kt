package com.wahidabd.onelibrary.data.firebase.raltime.model

import com.google.firebase.database.Exclude
import java.io.File


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeRequest(
    val name: String? = null,
    val age: Int? = null,
    val file: File? = null
){
    @Exclude
    fun toMap() = hashMapOf<String, Any?>(
        "name" to name,
        "age" to age
    )
}