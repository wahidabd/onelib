package com.wahidabd.onelibrary.data.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.OneFirebaseFirestore
import com.wahidabd.library.utils.firebase.OneFirebaseStorage
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


class OneFirebaseDataSource : FirebaseRepository, OneFirebaseFirestore(), OneFirebaseStorage {

    override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val storageReference: FirebaseStorage = FirebaseStorage.getInstance()
    override val storage: String = "user"


    override suspend fun addData(request: FirestoreRequest): Flow<Resource<Boolean>> =
        callbackFlow {
            setValue(
                value = request.toMap(),
                collection = "users",
                eventListener = { trySend(it) }
            )
            awaitClose { this.close() }
        }

    override suspend fun update(request: FirestoreRequest): Flow<Resource<Boolean>> = callbackFlow {
        val collection = "users"

        updateValue(
            id = request.id.toString(),
            collection = collection,
            value = request.toMap(),
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }

    override suspend fun getList(): Flow<Resource<List<FirestoreResponse>>> = callbackFlow {
        val collection = "users"

        getListValue(
            collection = collection,
            clazz = FirestoreResponse::class.java,
            eventListener = { trySend(it) }
        )

        awaitClose { this.close() }
    }

    override suspend fun getSingle(id: String): Flow<Resource<FirestoreResponse>> = callbackFlow {
        val document = "users"

        getSingleValue(
            id = id,
            collection = document,
            clazz = FirestoreResponse::class.java,
            eventListener = { trySend(it) },
        )

        awaitClose { this.close() }
    }

    override suspend fun remove(id: String): Flow<Resource<Boolean>> = callbackFlow {
        deleteValue(
            id = id,
            collection = "users",
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }
}