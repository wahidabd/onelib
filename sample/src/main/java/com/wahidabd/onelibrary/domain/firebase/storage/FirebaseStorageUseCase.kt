package com.wahidabd.onelibrary.domain.firebase.storage

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageData
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


interface FirebaseStorageUseCase {
    suspend fun getFiles(): Flow<Resource<List<StorageData>>>
    suspend fun uploadFile(body: StorageParam): Flow<Resource<Boolean>>
    suspend fun deleteFile(id: String): Flow<Resource<Boolean>>
}