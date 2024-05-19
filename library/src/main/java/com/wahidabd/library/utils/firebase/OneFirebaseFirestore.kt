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


/**
 * Example usage:
 * ```
 * class MyFirestore: OneFirebaseFirestore() {
 *   override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()
 * }
 * ```
 */
abstract class OneFirebaseFirestore {

    protected abstract val databaseRef: FirebaseFirestore


    /**
     * Sets data to a Firestore collection with the provided value and generates a unique document ID.
     *
     * @param value A `HashMap<String, Any?>` representing the data to be set. Keys are field names, and values are corresponding field values.
     * @param collection The name of the Firestore collection where the data will be stored.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Generates a unique document ID using Firestore's `document().id` method.
     * - Adds the generated ID to the provided value map.
     * - Sets the data to the Firestore collection using the generated ID.
     * - Invokes the `eventListener` callback with a success status if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val value = hashMapOf(
     *     "name" to "John Doe",
     *     "age" to 30,
     *     "email" to "john.doe@example.com"
     * )
     * val collection = "users"
     * setValue(collection, value) { result ->
     *    // handle the result
     * }
     * ```
     */
    fun setValue(
        collection: String,
        value: HashMap<String, Any?>,
        eventListener: ((data: Resource<Boolean>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        val id = databaseRef.collection(collection).document().id

        value["id"] = id
        databaseRef
            .collection(collection)
            .document(id)
            .set(value)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }

    /**
     * Updates data in a Firestore document within the specified collection using the provided ID and value.
     *
     * @param id The document ID identifying the document to be updated.
     * @param collection The name of the Firestore collection where the document resides.
     * @param value A `HashMap<String, Any?>` representing the fields to be updated. Keys are field names, and values are corresponding updated field values.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Updates the data in the Firestore document within the specified collection using the provided ID and value.
     * - Invokes the `eventListener` callback with a success status if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val id = "document_id"
     * val collection = "users"
     * val value = hashMapOf(
     *     "age" to 35,
     *     "email" to "updated.email@example.com"
     * )
     * updateValue(id, collection, value) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun updateValue(
        id: String,
        collection: String,
        value: HashMap<String, Any?>,
        eventListener: ((data: Resource<Boolean>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef
            .collection(collection)
            .document(id)
            .update(value)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }
            .addOnFailureListener { eventListener.invoke(Resource.fail(it.localizedMessage)) }
    }

    /**
     * Retrieves a single document from a Firestore collection using the provided document ID and collection name.
     *
     * @param id The document ID identifying the document to be retrieved.
     * @param collection The name of the Firestore collection where the document resides.
     * @param clazz The class of the object to which the retrieved document data will be mapped.
     * @param eventListener A callback function that takes a `Resource<T>` parameter, indicating the status of the operation and holding the retrieved data or error message.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Configures Firestore settings.
     * - Retrieves the document from the Firestore collection using the provided ID and collection name.
     * - Maps the retrieved document data to the specified class.
     * - Invokes the `eventListener` callback with the mapped data if the operation succeeds.
     * - Invokes the `eventListener` callback with an empty state if the document is null or not found.
     * - Invokes the `eventListener` callback with a failure state and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val id = "document_id"
     * val collection = "users"
     * getSingleValue(id, collection, User::class.java) { result ->
     *     // handle the result
     * }
     * ```
     */
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


    /**
     * Retrieves a list of documents from a Firestore collection and maps them to a list of objects of the specified class.
     *
     * @param collection The name of the Firestore collection from which documents will be retrieved.
     * @param clazz The class of the objects to which the retrieved documents will be mapped.
     * @param eventListener A callback function that takes a `Resource<List<T>>` parameter, indicating the status of the operation and holding the retrieved list of objects or error message.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Configures Firestore settings.
     * - Retrieves documents from the Firestore collection.
     * - Maps each retrieved document to an object of the specified class.
     * - Constructs a list of mapped objects.
     * - Invokes the `eventListener` callback with a success status and the list of mapped objects if the operation succeeds and the list is not empty.
     * - Invokes the `eventListener` callback with an empty state if the retrieved list is empty.
     * - Invokes the `eventListener` callback with a failure state and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val collection = "users"
     * getListValue(collection, User::class.java) { result ->
     *     // handle the result
     * }
     * ```
     */
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
                            results.add(result)
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
     * Deletes a document from a Firestore collection using the provided document ID and collection name.
     *
     * @param id The document ID identifying the document to be deleted.
     * @param collection The name of the Firestore collection from which the document will be deleted.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Deletes the document from the Firestore collection using the provided ID and collection name.
     * - Invokes the `eventListener` callback with a success status if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val id = "document_id"
     * val collection = "users"
     * deleteValue(id, collection) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun deleteValue(
        id: String,
        collection: String,
        eventListener: ((data: Resource<Boolean>) -> Unit),
    ) {
        eventListener.invoke(Resource.loading())

        val document = "$collection/$id"
        databaseRef
            .document(document)
            .delete()
            .addOnSuccessListener {
                try {
                    eventListener.invoke(
                        Resource.success(true)
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
     * Retrieves a list of documents from a Firestore collection based on a query and orders them by a specified field.
     *
     * @param query A pair representing the query field and value to filter documents.
     * @param collection The name of the Firestore collection from which documents will be retrieved.
     * @param orderBy The field by which the retrieved documents will be ordered.
     * @param clazz The class of the objects to which the retrieved documents will be mapped.
     * @param eventListener A callback function that takes a `Resource<List<T>>` parameter, indicating the status of the operation and holding the retrieved list of objects or error message.
     *
     * This function performs the following steps:
     * - Constructs a Firestore query to filter documents based on the provided query field and value.
     * - Orders the retrieved documents by the specified field in descending order.
     * - Retrieves documents from the Firestore collection based on the constructed query.
     * - Maps each retrieved document to an object of the specified class.
     * - Constructs a list of mapped objects.
     * - Invokes the `eventListener` callback with a success status and the list of mapped objects if the operation succeeds and the list is not empty.
     * - Invokes the `eventListener` callback with an empty state if the retrieved list is empty.
     * - Invokes the `eventListener` callback with a failure state and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val query = Pair("field", "value")
     * val collection = "users"
     * val orderBy = "createdAt"
     * getListQuery(query, collection, orderBy, User::class.java) { result ->
     *     // handle the result
     * }
     * ```
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
