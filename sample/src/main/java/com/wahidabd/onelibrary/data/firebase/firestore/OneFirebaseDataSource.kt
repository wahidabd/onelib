package com.wahidabd.onelibrary.data.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.OneFirebaseFirestore
import com.wahidabd.library.utils.firebase.pushImageToStorage
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


class OneFirebaseDataSource : FirebaseRepository, OneFirebaseFirestore() {
    override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun addData(request: FirestoreRequest): Flow<Resource<Boolean>> =
        callbackFlow {
            val collection = "users"
            val id = databaseRef.collection(collection).document().id
            request.id = id


            // push image to storage when the file is not null
            if (request.file != null) {
                pushImageToStorage("user", id, request.file, eventListener = { url ->
                    request.image = url

                    setValue(
                        value = request.toMap(),
                        collection = collection,
                        eventListener = { trySend(it) }
                    )
                })
            } else {
                setValue(
                    value = request.toMap(),
                    collection = collection,
                    eventListener = { trySend(it) }
                )
            }

            awaitClose { this.close() }
        }

    override fun update(request: FirestoreRequest): Flow<Resource<Boolean>> = callbackFlow {
        val collection = "users"

        updateValue(
            id = request.id.toString(),
            collection = collection,
            value = request.toMap(),
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }

    override fun getList(): Flow<Resource<List<FirestoreResponse>>> = callbackFlow {
        val collection = "users"

        getListValue(
            collection = collection,
            clazz = FirestoreResponse::class.java,
            eventListener = {
                trySend(it)
            }
        )

        awaitClose { this.close() }
    }

    override fun getSingle(id: String): Flow<Resource<FirestoreResponse>> = callbackFlow {
        val document = "users" //"users/$id"

        getSingleValue(
            id = id,
            collection = document,
            clazz = FirestoreResponse::class.java,
            eventListener = { trySend(it) },
        )

        awaitClose { this.close() }
    }

    override fun remove(id: String): Flow<Resource<Boolean>> = callbackFlow {
        deleteValue(
            id = id,
            collection = "users",
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }
}