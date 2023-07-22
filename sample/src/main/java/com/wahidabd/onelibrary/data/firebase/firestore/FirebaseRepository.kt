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

    fun addData(request: FirestoreRequest): Flow<Resource<GenericResponse>>
    fun update(request: FirestoreRequest): Flow<Resource<GenericResponse>>
    fun getList(): Flow<Resource<List<FirestoreResponse>>>
    fun getSingle(id: String): Flow<Resource<FirestoreResponse>>
    fun remove(id: String): Flow<Resource<GenericResponse>>


}