package com.wahidabd.library.utils.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.constant.OneConstant


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */

/**
 * Example usage:
 * ```
 * class MyRealtimeDatabase: OneFirebaseRealtime() {
 *   override val databaseRef: DatabaseReference = DatabaseReference.getInstance()
 * }
 * ```
 */
abstract class OneFirebaseRealtime {
    protected abstract val databaseRef: DatabaseReference

    /**
     * Sets data to a Firebase Realtime Database with the provided value.
     *
     * @param value A `HashMap<String, Any?>` representing the data to be set. Keys are field names, and values are corresponding field values.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Generates a unique ID for the data using the `push().key` method of DatabaseReference.
     * - Adds the generated ID to the provided value map.
     * - Sets the data to the Firebase Realtime Database using the generated ID.
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
     * setValue(value) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun setValue(
        value: HashMap<String, Any?>, eventListener: ((data: Resource<Boolean>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())

        val id = databaseRef.push().key.toString()
        value["id"] = id
        databaseRef.child(id).setValue(value).addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }.addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }


    /**
     * Updates data in a Firebase Realtime Database with the provided value.
     *
     * @param id The ID of the data to be updated.
     * @param value A `HashMap<String, Any?>` representing the fields and their updated values.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Updates the data in the Firebase Realtime Database with the provided value.
     * - Invokes the `eventListener` callback with a success status if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val id = "unique_id"
     * val value = hashMapOf(
     *     "name" to "Updated Name",
     *     "age" to 35,
     *     "email" to "updated.email@example.com"
     * )
     * updateValue(id, value) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun updateValue(
        id: String, value: HashMap<String, Any?>, eventListener: ((data: Resource<Boolean>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.child(id).updateChildren(value).addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }.addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }


    /**
     * Removes data from a Firebase Realtime Database.
     *
     * @param id The ID of the data to be removed.
     * @param eventListener A callback function that takes a `Resource<Boolean>` parameter, indicating the status of the operation.
     *
     * This function performs the following steps:
     * - Invokes the `eventListener` callback with a loading status using `Resource.loading()`.
     * - Removes the data from the Firebase Realtime Database.
     * - Invokes the `eventListener` callback with a success status if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * val id = "unique_id"
     * removeValue(id) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun removeValue(
        id: String, eventListener: ((data: Resource<Boolean>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        databaseRef.child(id).removeValue().addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }.addOnFailureListener { error ->
                eventListener.invoke(Resource.fail(error.localizedMessage))
            }
    }


    /**
     * Retrieves a list of data from a Firebase Realtime Database and maps them to objects of the specified class.
     *
     * @param clazz The class of the objects to which the retrieved data will be mapped.
     * @param eventListener A callback function that takes a `Resource<List<T>>` parameter, indicating the status of the operation and holding the retrieved list of objects or error message.
     *
     * This function performs the following steps:
     * - Registers a ValueEventListener to listen for changes in the Firebase Realtime Database.
     * - When data changes, maps each child snapshot to an object of the specified class and constructs a list of mapped objects.
     * - Invokes the `eventListener` callback with a success status and the list of mapped objects if the operation succeeds and the list is not empty.
     * - Invokes the `eventListener` callback with an empty state if the retrieved list is empty.
     * - Invokes the `eventListener` callback with a failure state and the error message if the operation fails.
     *
     * Example usage:
     * ```
     * getListValue(User::class.java) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun <T> getListValue(
        clazz: Class<T>,
        eventListener: ((data: Resource<List<T>>) -> Unit),
    ) {
        try {
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = ArrayList<T>()

                    for (i in snapshot.children) {
                        val data = i.getValue(clazz)
                        if (data != null) list.add(data)
                    }

                    if (list.isEmpty()) eventListener.invoke(Resource.empty())
                    else eventListener.invoke(Resource.success(list))
                }

                override fun onCancelled(error: DatabaseError) {
                    eventListener.invoke(Resource.fail(error.message))
                }
            })
        } catch (e: Exception) {
            eventListener.invoke(Resource.fail(e.localizedMessage))
        }
    }


    /**
     * Retrieves a single value from a specified child location in a Firebase Realtime Database and maps it to an object of the specified class.
     *
     * @param clazz The class of the object to which the retrieved data will be mapped.
     * @param id The path to the child location from which the value will be retrieved.
     * @param eventListener A callback function that takes a `Resource<T>` parameter, indicating the status of the operation and holding the retrieved object or error message.
     *
     * This function performs the following steps:
     * - Registers a ValueEventListener to retrieve a single value from the specified child location in the Firebase Realtime Database.
     * - When the value is retrieved, maps it to an object of the specified class.
     * - Invokes the `eventListener` callback with a success status and the mapped object if the operation succeeds.
     * - Invokes the `eventListener` callback with a failure status and an error message if the operation fails or if the data is not found.
     *
     * Example usage:
     * ```
     * getValue("user_id", User::class.java) { result ->
     *     // handle the result
     * }
     * ```
     */
    fun <T> getValue(
        id: String,
        clazz: Class<T>,
        eventListener: ((data: Resource<T>) -> Unit),
    ) {
        try {
            databaseRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = snapshot.getValue(clazz)
                    if (result != null) {
                        eventListener.invoke(Resource.success(result))
                    } else {
                        eventListener.invoke(Resource.fail(OneConstant.MESSAGE_DATA_NOT_FOUND))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    eventListener.invoke(Resource.fail(error.message))
                }

            })
        } catch (e: Exception) {
            eventListener.invoke(Resource.fail(e.localizedMessage))
        }
    }
}