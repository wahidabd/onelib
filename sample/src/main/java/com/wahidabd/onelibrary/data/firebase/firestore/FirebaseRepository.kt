package com.wahidabd.onelibrary.data.firebase.firestore

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreResponse
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


interface FirebaseRepository {

    suspend fun addData(request: FirestoreRequest): Flow<Resource<Boolean>>
    suspend fun update(request: FirestoreRequest): Flow<Resource<Boolean>>
    suspend fun getList(): Flow<Resource<List<FirestoreResponse>>>
    suspend fun getSingle(id: String): Flow<Resource<FirestoreResponse>>
    suspend fun remove(id: String): Flow<Resource<Boolean>>


}