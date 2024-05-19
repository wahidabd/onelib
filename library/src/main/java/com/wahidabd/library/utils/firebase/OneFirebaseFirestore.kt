package com.wahidabd.library.utils.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.constant.OneConstant
import com.wahidabd.library.utils.coroutine.handler.GenericResponse


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


abstract class OneFirebaseFirestore {
    protected abstract val databaseRef: FirebaseFirestore


    fun setValue(
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
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(OneConstant.MESSAGE_FAILED_WRITE))
            }
    }

    /**
     * the request must be full data to update data
     */
    fun updateValue(
        id: String,
        collection: String,
        value: HashMap<String, Any?>,
        eventListener: ((data: Resource<GenericResponse>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef
            .collection(collection)
            .document(id)
            .update(value)
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
    fun <T> getSingleValue(
        id: String,
        collection: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<T>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        databaseRef
            .document("$collection/$id")
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

    fun <T> getListValue(
        collection: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<List<T>>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
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
     * @param collection like 'collection/id' (add slash (/) for separator
     * @param eventListener This is lambda function for collect the data from firestore
     * */
    fun deleteValue(
        document: String,
        eventListener: ((data: Resource<GenericResponse>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
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


    /**
     * if you use this query, you must be passing query data with TRIPLE.
     * @param [query] first The path of the field to compare
     * @param [query] second The value for comparison
     * @param [orderBy] The value for order the data from descending
     * @return The data of list [clazz]
     */
    fun <T> getListQuery(
        query: Pair<String, String>,
        collection: String,
        orderBy: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<List<T>>) -> Unit)
    ) {
        val results = ArrayList<T>()

        databaseRef.collection(collection)
            .whereEqualTo(query.first, query.second)
            .orderBy(orderBy, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { document ->
                try {
                    document.map { snapshot ->
                        val res = snapshot.toObject(clazz)
                        results.add(res)
                    }

                    if (results.isEmpty()) eventListener.invoke(Resource.empty())
                    else eventListener.invoke(Resource.success(results))
                } catch (e: Exception) {
                    eventListener.invoke(Resource.fail(e.localizedMessage))
                }
            }
            .addOnFailureListener { exception ->
                eventListener.invoke(Resource.fail(exception.localizedMessage))
            }
    }
}
