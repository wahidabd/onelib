package com.wahidabd.onelibrary.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.FirebaseFirestoreManager
import com.wahidabd.onelibrary.data.firebase.model.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.FirestoreResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


class FirebaseDataSource : FirebaseRepository, FirebaseFirestoreManager() {
    override val databaseRef: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun addData(request: FirestoreRequest): Flow<Resource<Boolean>> = callbackFlow {
        val collection = "users"
        val id = databaseRef.collection(collection).document().id
        request.id = id

        writeData(
            id = id,
            value = request.toMap(),
            collection = collection,
            onSuccess = {
                trySend(Resource.success(it))
            },
            onError = {
                trySend(Resource.fail(it.localizedMessage))
            }
        )

        awaitClose { this.close() }
    }

    override fun getList(): Flow<Resource<List<FirestoreResponse>>> = callbackFlow {
        val collection = "users"

        readListData(
            collection,
            FirestoreResponse::class.java,
            onSuccess = {
                trySend(it)
            },
            onError = {
                trySend(Resource.fail(it.localizedMessage))
            }
        )

        awaitClose { this.close() }
    }

    override fun getSingle(id: String): Flow<Resource<FirestoreResponse>> = callbackFlow {
        val document = "users/$id"

        readSingleData(
            document = document,
            FirestoreResponse::class.java,
            onSuccess = {trySend(it)},
            onError = {trySend(Resource.fail(it.localizedMessage))}
        )

        awaitClose { this.close() }
    }

    override fun remove(id: String): Flow<Resource<Boolean>> = callbackFlow{
        val document = "users/$id"
        removeData(
            document = document,
            onSuccess = {trySend(it)},
            onError = {trySend(Resource.fail(it.localizedMessage))}
        )

        awaitClose { this.close() }
    }


}