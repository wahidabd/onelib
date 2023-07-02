package com.wahidabd.onelibrary.domain.firebase

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseRepository
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreResponse
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import com.wahidabd.onelibrary.domain.firebase.model.toDomain
import com.wahidabd.onelibrary.domain.firebase.model.toRequest
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


class FirestoreInteractor(private val repo: FirebaseRepository) : FirestoreUseCase {

    override fun writeData(request: FirestoreParam): Flow<Resource<GenericResponse>> {
        return repo.addData(request.toRequest())
    }

    override fun getList(): Flow<Resource<List<FirestoreData>>> {
        return object : InternetBoundResource<List<FirestoreData>, List<FirestoreResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<FirestoreResponse>>> {
                return repo.getList()
            }

            override suspend fun saveCallRequest(data: List<FirestoreResponse>): List<FirestoreData> {
                return data.map { it.toDomain() }
            }

        }.asFlow()
    }

    override fun getSingle(id: String): Flow<Resource<FirestoreData>> {
        return object : InternetBoundResource<FirestoreData, FirestoreResponse>() {
            override suspend fun createCall(): Flow<Resource<FirestoreResponse>> {
                return repo.getSingle(id)
            }

            override suspend fun saveCallRequest(data: FirestoreResponse): FirestoreData {
                return data.toDomain()
            }

        }.asFlow()
    }

    override fun remove(id: String): Flow<Resource<GenericResponse>> =
        repo.remove(id)
}