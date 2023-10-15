package com.wahidabd.onelibrary.data.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.library.utils.firebase.FirebaseFirestoreManager
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


class FirebaseDataSource : FirebaseRepository, FirebaseFirestoreManager() {
    override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun addData(request: FirestoreRequest): Flow<Resource<GenericResponse>> =
        callbackFlow {
            val collection = "users"
            val id = databaseRef.collection(collection).document().id
            request.id = id


            // push image to storage when the file is not null
            if (request.file != null) {
                pushImageToStorage("user", id, request.file, eventListener = {url ->
                    request.image = url

                    setValue(
                        id = id,
                        value = request.toMap(),
                        collection = collection,
                        eventListener = { trySend(it) }
                    )
                })
            } else {
                setValue(
                    id = id,
                    value = request.toMap(),
                    collection = collection,
                    eventListener = { trySend(it) }
                )
            }

            awaitClose { this.close() }
        }

    override fun update(request: FirestoreRequest): Flow<Resource<GenericResponse>> = callbackFlow {
        val collection = "users"

        updateValue(
            id = request.id.toString(),
            collection = collection,
            value = request.toMap(),
            eventListener = {trySend(it)}
        )
        awaitClose { this.close() }
    }

    override fun getList(): Flow<Resource<List<FirestoreResponse>>> = callbackFlow {
        val collection = "users"

        getListValue(
            collection,
            FirestoreResponse::class.java,
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
            FirestoreResponse::class.java,
            eventListener = { trySend(it) },
        )

        awaitClose { this.close() }
    }

    override fun remove(id: String): Flow<Resource<GenericResponse>> = callbackFlow {
        deleteValue(
            document = id,
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }
}