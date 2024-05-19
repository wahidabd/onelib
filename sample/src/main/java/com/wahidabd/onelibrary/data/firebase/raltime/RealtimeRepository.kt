package com.wahidabd.onelibrary.data.firebase.raltime

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


interface RealtimeRepository {
    fun realtimeAdd(data: RealtimeRequest): Flow<Resource<Boolean>>
    fun realtimeRemove(id: String): Flow<Resource<Boolean>>
    fun realtimeList(): Flow<Resource<List<RealtimeResponse>>>
}