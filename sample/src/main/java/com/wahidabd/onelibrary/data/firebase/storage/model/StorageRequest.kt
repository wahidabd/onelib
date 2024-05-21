package com.wahidabd.onelibrary.data.firebase.storage.model

import com.google.firebase.firestore.Exclude
import com.wahidabd.library.utils.common.emptyString
import java.io.File


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


data class StorageRequest(
    val name: String,
    val file: File,
    var url: String? = emptyString()
) {
    @Exclude
    fun toHashMap(): HashMap<String, Any?> = hashMapOf(
        "name" to name,
        "file" to url
    )
}
