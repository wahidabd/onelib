package com.wahidabd.library.utils.coroutine.boundResource

import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.*

/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

abstract class NetworkBoundResource<Response, Request> {

    private var resource: Flow<Resource<Response>> = flow {
        emit(Resource.loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.loading())
            when (val response = createCall().first()) {
                is Resource.Default -> {
                    emit(Resource.default())
                }
                is Resource.Loading -> {
                    emit(Resource.loading())
                }
                is Resource.Empty -> {
                    emit(Resource.empty())
                }
                is Resource.Failure -> {
                    onFetchFailed()
                    emit(Resource.fail(response.message))
                }
                is Resource.Success -> {
                    saveCallRequest(response.data)
                    emitAll(loadFromDB().map {
                        Resource.success(it)
                    })
                }
            }
        }
        else{
            emitAll(loadFromDB().map { Resource.success(it) })
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<Response>
    protected abstract fun shouldFetch(data: Response?): Boolean
    protected abstract suspend fun createCall(): Flow<Resource<Request>>
    protected abstract suspend fun saveCallRequest(data: Request)
    fun asFlow(): Flow<Resource<Response>> = resource
}