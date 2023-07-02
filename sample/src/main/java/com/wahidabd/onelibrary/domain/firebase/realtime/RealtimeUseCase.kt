package com.wahidabd.onelibrary.domain.firebase.realtime

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.data.firebase.model.realtime.RealtimeRequest
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeData
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


interface RealtimeUseCase {
    fun realtimeAdd(data: RealtimeParam): Flow<Resource<GenericResponse>>
    fun realtimeRemove(id: String): Flow<Resource<GenericResponse>>
    fun realtimeList(): Flow<Resource<List<RealtimeData>>>
}