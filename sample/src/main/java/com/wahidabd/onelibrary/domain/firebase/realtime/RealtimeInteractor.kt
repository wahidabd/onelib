package com.wahidabd.onelibrary.domain.firebase.realtime

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeResponse
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeRepository
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeData
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import com.wahidabd.onelibrary.domain.firebase.model.toDomain
import com.wahidabd.onelibrary.domain.firebase.model.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeInteractor(
    private val repository: RealtimeRepository
) : RealtimeUseCase {

    override fun realtimeAdd(data: RealtimeParam): Flow<Resource<GenericResponse>> {
        return repository.realtimeAdd(data.toRequest())
    }

    override fun realtimeRemove(id: String): Flow<Resource<GenericResponse>> {
        return repository.realtimeRemove(id)
    }

    override fun realtimeList(): Flow<Resource<List<RealtimeData>>> {
        return object : InternetBoundResource<List<RealtimeData>, List<RealtimeResponse>>(){
            override suspend fun createCall(): Flow<Resource<List<RealtimeResponse>>> {
                return repository.realtimeList()
            }

            override suspend fun saveCallRequest(data: List<RealtimeResponse>): List<RealtimeData> {
                return data.map {
                    it.toDomain()
                }
            }

        }.asFlow()
    }
}