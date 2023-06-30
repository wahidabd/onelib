package com.wahidabd.library.utils.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


abstract class FirebaseFirestoreManager {
    protected abstract val databaseRef: FirebaseFirestore


    fun writeData(
        id: String,
        value: Any,
        collection: String,
        onSuccess: ((Boolean) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null
    ) {
        databaseRef
            .collection(collection)
            .document(id)
            .set(value)
            .addOnSuccessListener {
                debug { "Firestore Reference ${it}" }
                onSuccess?.invoke(true)
            }
            .addOnFailureListener { onError?.invoke(it) }
    }

    /**
    * send document as your collection and document id,
    * like collection/id (add slash (/) for separator
    * example: users/user_id
    * */
    fun <T> readSingleData(
        document: String,
        clazz: Class<T>,
        onSuccess: ((data: Resource<T>) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null
    ){
        databaseRef.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        databaseRef
            .document(document)
            .get()
            .addOnSuccessListener { result ->
                try {
                    if (result != null) {
                        val data = result.toObject(clazz)
                        if (data != null) onSuccess?.invoke(Resource.success(data))
                        else onSuccess?.invoke(Resource.empty())
                    }
                }catch (e: Exception){
                    onError?.invoke(e)
                }
            }
            .addOnFailureListener {
                onError?.invoke(it)
            }
    }

    fun <T> readListData(
        collection: String,
        clazz: Class<T>,
        onSuccess: ((data: Resource<List<T>>) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
    ) {
        databaseRef.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val results = ArrayList<T>()

        databaseRef
            .collection(collection)
            .get()
            .addOnSuccessListener { document ->
                try {
                    if (document != null) {
                        for (i in document) {
                            val result = i.toObject(clazz)
                            if (result != null) results.add(result)
                        }

                        if (results.isEmpty()) onSuccess?.invoke(Resource.empty())
                        else onSuccess?.invoke(Resource.success(results))

                    }
                } catch (e: Exception) {
                    onError?.invoke(e)
                }
            }
            .addOnFailureListener {
                onError?.invoke(it)
            }
    }

    /**
     * send document as your collection and document id,
     * like collection/id (add slash (/) for separator
     * example: users/user_id
     * */
    fun removeData(
        document: String,
        onSuccess: ((data: Resource<Boolean>) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null
    ){
        databaseRef
            .document(document)
            .delete()
            .addOnSuccessListener {
                try {
                    onSuccess?.invoke(Resource.success(true))
                }catch (e: Exception){
                    onError?.invoke(e)
                }
            }
            .addOnFailureListener {
                onError?.invoke(it)
            }
    }
}
