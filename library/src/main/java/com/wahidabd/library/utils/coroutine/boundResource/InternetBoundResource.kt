package com.wahidabd.library.utils.coroutine.boundResource

import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


/**
 * Created by Wahid on 6/10/2023.
 * Github github.com/wahidabd.
 */


abstract class InternetBoundResource<Response, Request> {

    private var resource: Flow<Resource<Response>> = flow {
        emit(Resource.loading())
        when(val response = createCall().first()){
            is Resource.Default -> {}
            is Resource.Empty -> {
                emit(Resource.empty())
            }
            is Resource.Loading -> {
                emit(Resource.loading())
            }
            is Resource.Failure -> {
                onFetchFailed()
                emit(Resource.fail(response.message))
            }
            is Resource.Success -> {
                emit(Resource.success(saveCallRequest(response.data)))
            }
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract suspend fun createCall(): Flow<Resource<Request>>
    protected abstract suspend fun saveCallRequest(data: Request): Response
    fun asFlow(): Flow<Resource<Response>> = resource
}