package com.wahidabd.onelibrary.data.firebase

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.model.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.FirestoreResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


interface FirebaseRepository {

    fun addData(request: FirestoreRequest): Flow<Resource<Boolean>>
    fun getList(): Flow<Resource<List<FirestoreResponse>>>
    fun getSingle(id: String): Flow<Resource<FirestoreResponse>>
    fun remove(id: String): Flow<Resource<Boolean>>
}