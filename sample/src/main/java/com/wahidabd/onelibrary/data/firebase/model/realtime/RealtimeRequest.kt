package com.wahidabd.onelibrary.data.firebase.model.realtime

import com.google.firebase.database.Exclude
import java.io.File


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


data class RealtimeRequest(
    var id: String? = null,
    val name: String? = null,
    val age: Int? = null,
    val file: File? = null
){
    @Exclude
    fun toMap() = hashMapOf<String, Any?>(
        "id" to id,
        "name" to name,
        "age" to age
    )
}