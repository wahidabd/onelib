package com.wahidabd.onelibrary.domain.firebase.firestore

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.library.utils.coroutine.oneMap
import com.wahidabd.library.utils.coroutine.oneMapList
import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseRepository
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreResponse
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import com.wahidabd.onelibrary.domain.firebase.model.toDomain
import com.wahidabd.onelibrary.domain.firebase.model.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


class FirestoreInteractor(private val repo: FirebaseRepository) : FirestoreUseCase {

    override suspend fun writeData(request: FirestoreParam): Flow<Resource<Boolean>> {
        return repo.addData(request.toRequest())
    }

    override suspend fun update(request: FirestoreRequest): Flow<Resource<Boolean>> {
        return repo.update(request)
    }

    override suspend fun getList(): Flow<Resource<List<FirestoreData>>> {
        return repo.getList().map { resource ->
            resource.oneMapList { data -> data.toDomain() }
        }
    }

    override suspend fun getSingle(id: String): Flow<Resource<FirestoreData>> {
        return repo.getSingle(id).map { resource ->
            resource.oneMap { data -> data.toDomain() }
        }
    }

    override suspend fun remove(id: String): Flow<Resource<Boolean>> =
        repo.remove(id)
}