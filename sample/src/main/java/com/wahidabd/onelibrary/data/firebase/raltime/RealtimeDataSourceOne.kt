package com.wahidabd.onelibrary.data.firebase.raltime

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.OneFirebaseRealtime
import com.wahidabd.onelibrary.data.firebase.raltime.model.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.raltime.model.RealtimeResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeDataSourceOne : RealtimeRepository, OneFirebaseRealtime() {

    override val databaseRef: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("users")

    override suspend fun realtimeAdd(data: RealtimeRequest): Flow<Resource<Boolean>> =
        callbackFlow {
            setValue(value = data.toMap()) {
                trySend(it)
            }

            awaitClose { this.close() }
        }

    override suspend fun realtimeRemove(id: String): Flow<Resource<Boolean>> = callbackFlow {
        removeValue(id = id) {
            trySend(it)
        }

        awaitClose { this.close() }
    }

    override suspend fun realtimeList(): Flow<Resource<List<RealtimeResponse>>> = callbackFlow {
        getListValue(
            clazz = RealtimeResponse::class.java,
            eventListener = { trySend(it) }
        )
        awaitClose { this.close() }
    }

    override suspend fun realtimeEdit(id: String): Flow<Resource<Boolean>> = callbackFlow {
        val value = hashMapOf<String, Any?>("name" to "wahid")

        updateValue(id = id, value = value) {
            trySend(it)
        }

        awaitClose(this::close)
    }

    override suspend fun getData(id: String): Flow<Resource<RealtimeResponse>> = callbackFlow {
        getValue(id = id, clazz = RealtimeResponse::class.java){ data ->
            trySend(data)
        }

        awaitClose(this::close)
    }
}