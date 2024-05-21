package com.wahidabd.library.utils.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.wahidabd.library.utils.common.emptyString
import java.io.File


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


/**
 * Example usage:
 * ```
 * class MyRealtimeDatabase: OneFirebaseRealtime {
 *   override val storageReference: FirebaseStorage = FirebaseStorage.getInstance()
 *   override val storage: String = "user" // your storage name
 * }
 * ```
 */
interface OneFirebaseStorage {
    val storageReference: FirebaseStorage
    val storage: String

    /**
     * Uploads a file to Firebase Storage.
     *
     * @param file The File object representing the file to be uploaded.
     * @param child The path to the child location in Firebase Storage where the file will be stored.
     * @param eventListener A callback function that takes a String parameter, representing the download URL of the uploaded file or an error message.
     *
     * This function performs the following steps:
     * - Converts the File object to a Uri.
     * - Uploads the file to the specified child location in Firebase Storage.
     * - Upon successful upload, retrieves the download URL of the uploaded file.
     * - Invokes the `eventListener` callback with the download URL if the upload succeeds.
     * - Invokes the `eventListener` callback with an error message if the upload fails.
     *
     * Example usage:
     * ```
     * val file = File("path/to/file")
     * pushFile(file, "images") { url ->
     *     // Handle the download URL or error message
     * }
     * ```
     */
    fun pushFile(
        file: File,
        child: String,
        eventListener: (String) -> Unit
    ) {
        val uriFile = Uri.fromFile(file)

        storageReference.getReference(storage).child(child).putFile(uriFile)
            .addOnSuccessListener {
                storageReference.getReference(storage).child(child).downloadUrl.addOnSuccessListener { url ->
                    eventListener.invoke(url.toString())
                }
            }
            .addOnFailureListener {
                eventListener.invoke(it.localizedMessage ?: emptyString())
            }
    }


    /**
     * Deletes a file from Firebase Storage.
     *
     * @param id The path to the child location in Firebase Storage where the file is stored.
     * @param eventListener A callback function that takes a Boolean parameter, indicating whether the file deletion operation was successful.
     *
     * This function performs the following steps:
     * - Deletes the file located at the specified child location in Firebase Storage.
     * - Invokes the `eventListener` callback with `true` if the deletion succeeds.
     * - Invokes the `eventListener` callback with `false` if the deletion fails.
     *
     * Example usage:
     * ```
     * removeFile("images/file.jpg") { isSuccess ->
     *     // Handle the success or failure of file deletion
     * }
     * ```
     */
    fun removeFile(
        id: String,
        eventListener: (Boolean) -> Unit
    ) {
        storageReference.getReference(storage).child(id).delete()
            .addOnSuccessListener {
                eventListener.invoke(true)
            }
            .addOnFailureListener {
                eventListener.invoke(false)
            }
    }
}
