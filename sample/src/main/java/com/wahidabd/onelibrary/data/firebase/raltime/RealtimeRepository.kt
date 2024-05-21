package com.wahidabd.onelibrary.data.firebase.raltime

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.raltime.model.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.raltime.model.RealtimeResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


interface RealtimeRepository {
    suspend fun realtimeAdd(data: RealtimeRequest): Flow<Resource<Boolean>>
    suspend fun realtimeRemove(id: String): Flow<Resource<Boolean>>
    suspend fun realtimeList(): Flow<Resource<List<RealtimeResponse>>>
    suspend fun realtimeEdit(id: String): Flow<Resource<Boolean>>
    suspend fun getData(id: String): Flow<Resource<RealtimeResponse>>
}