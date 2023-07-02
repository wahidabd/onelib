package com.wahidabd.library.utils.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import java.io.File


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


/**
 * this is function for upload image (format file) to firebase storage and return url as a string
 */
fun pushImageToStorage(
    storage: String,
    child: String,
    file: File,
    eventListener: ((String) -> Unit)
) {

    val myStorage = FirebaseStorage.getInstance().getReference(storage)
    val uriFile = Uri.fromFile(file)

    myStorage.child(child).putFile(uriFile)
        .addOnSuccessListener {
            myStorage.child(child).downloadUrl.addOnSuccessListener { url ->
                eventListener.invoke(url.toString())
            }
        }

}
