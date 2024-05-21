package com.wahidabd.onelibrary.domain.firebase.firestore

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


interface FirestoreUseCase {
    suspend fun writeData(request: FirestoreParam): Flow<Resource<Boolean>>
    suspend fun update(request: FirestoreRequest): Flow<Resource<Boolean>>
    suspend fun getList(): Flow<Resource<List<FirestoreData>>>
    suspend fun getSingle(id: String): Flow<Resource<FirestoreData>>
    suspend fun remove(id: String): Flow<Resource<Boolean>>
}