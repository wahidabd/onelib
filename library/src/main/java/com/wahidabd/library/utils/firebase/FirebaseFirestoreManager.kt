package com.wahidabd.library.utils.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.constant.OneConstant
import com.wahidabd.library.utils.coroutine.handler.GenericResponse


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


abstract class FirebaseFirestoreManager {
    protected abstract val databaseRef: FirebaseFirestore


    fun writeData(
        id: String,
        value: HashMap<String, Any?>,
        collection: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef
            .collection(collection)
            .document(id)
            .set(value)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(
                        GenericResponse(
                            true,
                            OneConstant.MESSAGE_SUCCESS_WRITE
                        )
                    )
                )
            }
            .addOnFailureListener { eventListener.invoke(Resource.fail(it.localizedMessage)) }
    }

    /**
     * send document as your collection and document id,
     * like collection/id (add slash (/) for separator
     * example: users/user_id
     * */
    fun <T> readSingleData(
        document: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<T>) -> Unit)
    ) {
        databaseRef.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        databaseRef
            .document(document)
            .get()
            .addOnSuccessListener { result ->
                try {
                    if (result != null) {
                        val data = result.toObject(clazz)
                        if (data != null) eventListener.invoke(Resource.success(data))
                        else eventListener.invoke(Resource.empty())
                    }
                } catch (e: Exception) {
                    eventListener.invoke(Resource.fail(e.localizedMessage))
                }
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }

    fun <T> readListData(
        collection: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<List<T>>) -> Unit)
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

                        if (results.isEmpty()) eventListener.invoke(Resource.empty())
                        else eventListener.invoke(Resource.success(results))

                    }
                } catch (e: Exception) {
                    eventListener.invoke(Resource.fail(e.localizedMessage))
                }
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }

    /**
     * send document as your collection and document id,
     * like collection/id (add slash (/) for separator
     * example: users/user_id
     * */
    fun removeData(
        document: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit),
    ) {
        databaseRef
            .document(document)
            .delete()
            .addOnSuccessListener {
                try {
                    eventListener.invoke(
                        Resource.success(
                            GenericResponse(
                                true,
                                OneConstant.MESSAGE_SUCCESS_REMOVE
                            )
                        )
                    )
                } catch (e: Exception) {
                    eventListener.invoke(Resource.fail(e.localizedMessage))
                }
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }
}
