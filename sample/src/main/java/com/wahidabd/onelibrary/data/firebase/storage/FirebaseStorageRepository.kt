package com.wahidabd.onelibrary.data.firebase.storage

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.storage.model.StorageRequest
import com.wahidabd.onelibrary.data.firebase.storage.model.StorageResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


interface FirebaseStorageRepository {
    suspend fun getFiles(): Flow<Resource<List<StorageResponse>>>
    suspend fun uploadFile(body: StorageRequest): Flow<Resource<Boolean>>
    suspend fun deleteFile(id: String): Flow<Resource<Boolean>>
}