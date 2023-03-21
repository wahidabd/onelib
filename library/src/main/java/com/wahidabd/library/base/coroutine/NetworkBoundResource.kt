package com.wahidabd.library.base.coroutine

import com.wahidabd.library.data.Result
import kotlinx.coroutines.flow.*

/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

abstract class NetworkBoundResource<Response, Request> {

    private var result: Flow<Result<Response>> = flow {
        emit(Result.loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Result.loading())
            when (val response = createCall().first()) {
                is Result.Default -> {}
                is Result.Loading -> {}
                is Result.Empty -> {}
                is Result.Failure -> {
                    onFetchFailed()
                    emit(Result.fail(response.message))
                }
                is Result.Success -> {
                    saveCallRequest(response.data)
                    emitAll(loadFromDB().map {
                        Result.success(it)
                    })
                }
            }
        }else{
            emitAll(loadFromDB().map { Result.success(it) })
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<Response>
    protected abstract fun shouldFetch(data: Response?): Boolean
    protected abstract suspend fun createCall(): Flow<Result<Request>>
    protected abstract suspend fun saveCallRequest(data: Request)
    fun asFlow(): Flow<Result<Response>> = result
}