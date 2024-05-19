package com.wahidabd.library.utils.coroutine

import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * Created by Wahid on 3/27/2023.
 * Github wahidabd.
 */

abstract class EmitResource <Request, Response> {

    private var result: Flow<Resource<Response>> = flow {
        emit(Resource.Loading())
        when(val response = createCall().first()){
            is Resource.Loading -> {}
            is Resource.Default -> {}
            is Resource.Empty -> {}
            is Resource.Failure -> {
                emit(Resource.fail(response.message))
            }
            is Resource.Success -> {
                saveCallResult(response.data)
            }
        }
    }


    protected abstract suspend fun createCall(): Flow<Resource<Request>>
    protected abstract suspend fun saveCallResult(data: Request)
    fun asFlow(): Flow<Resource<Response>> = result
}