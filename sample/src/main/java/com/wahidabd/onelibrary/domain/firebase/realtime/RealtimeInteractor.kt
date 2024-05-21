package com.wahidabd.onelibrary.domain.firebase.realtime

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.oneMap
import com.wahidabd.library.utils.coroutine.oneMapList
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

    override suspend fun realtimeAdd(data: RealtimeParam): Flow<Resource<Boolean>> {
        return repository.realtimeAdd(data.toRequest())
    }

    override suspend fun realtimeRemove(id: String): Flow<Resource<Boolean>> {
        return repository.realtimeRemove(id)
    }

    override suspend fun realtimeList(): Flow<Resource<List<RealtimeData>>> {
        return repository.realtimeList().map { resource ->
            resource.oneMapList { data -> data.toDomain() }
        }
    }

    override suspend fun realtimeEdit(id: String): Flow<Resource<Boolean>> {
        return repository.realtimeEdit(id)
    }

    override suspend fun realtimeData(id: String): Flow<Resource<RealtimeData>> {
        return repository.getData(id).map { resource ->
            resource.oneMap { data -> data.toDomain() }
        }
    }
}