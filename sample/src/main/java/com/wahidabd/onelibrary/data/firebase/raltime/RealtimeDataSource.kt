package com.wahidabd.onelibrary.data.firebase.raltime

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.library.utils.firebase.FirebaseRealtimeManager
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeDataSource : RealtimeRepository, FirebaseRealtimeManager() {

    override val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    override fun realtimeAdd(data: RealtimeRequest): Flow<Resource<GenericResponse>> = callbackFlow{
        val id = databaseRef.push().key
        data.id = id.toString()

        setValue(
            data.toMap(),
            child = id.toString(),
            eventListener = {
                trySend(it)
            }
        )

        awaitClose { this.close() }
    }

    override fun realtimeRemove(id: String): Flow<Resource<GenericResponse>> = callbackFlow {
        removeValue(
            child = id,
            eventListener = {
                trySend(it)
            }
        )

        awaitClose { this.close() }
    }

    override fun realtimeList(): Flow<Resource<List<RealtimeResponse>>> = callbackFlow{
        addListValueEventListener(
            clazz = RealtimeResponse::class.java,
            eventListener = {trySend(it)}
        )
        awaitClose { this.close() }
    }
}