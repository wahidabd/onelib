package com.wahidabd.onelibrary.domain.firebase.realtime

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.realtime.model.RealtimeData
import com.wahidabd.onelibrary.domain.firebase.realtime.model.RealtimeParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


interface RealtimeUseCase {
    suspend fun realtimeAdd(data: RealtimeParam): Flow<Resource<Boolean>>
    suspend fun realtimeRemove(id: String): Flow<Resource<Boolean>>
    suspend fun realtimeList(): Flow<Resource<List<RealtimeData>>>
    suspend fun realtimeEdit(id: String): Flow<Resource<Boolean>>
    suspend fun realtimeData(id: String): Flow<Resource<RealtimeData>>
}