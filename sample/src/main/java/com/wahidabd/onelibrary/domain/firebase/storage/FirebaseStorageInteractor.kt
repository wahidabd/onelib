package com.wahidabd.onelibrary.domain.firebase.storage

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.exts.oneMapList
import com.wahidabd.onelibrary.data.firebase.storage.FirebaseStorageRepository
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageData
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageParam
import com.wahidabd.onelibrary.domain.firebase.storage.model.mapper.toDomain
import com.wahidabd.onelibrary.domain.firebase.storage.model.mapper.toReq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseStorageInteractor(private val repository: FirebaseStorageRepository) : FirebaseStorageUseCase {
    override suspend fun getFiles(): Flow<Resource<List<StorageData>>> {
        return repository.getFiles().map {
            it.oneMapList { data -> data.toDomain() }
        }
    }

    override suspend fun uploadFile(body: StorageParam): Flow<Resource<Boolean>> {
        return repository.uploadFile(body.toReq())
    }

    override suspend fun deleteFile(id: String): Flow<Resource<Boolean>> {
        return repository.deleteFile(id)
    }
}