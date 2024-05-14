package com.wahidabd.onelibrary.data.movie

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.OneCall
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieDataStore(
    api: MovieApi,
    private val errorParser: ErrorParser,
) : MovieRepository {

    override val dbService: LocalDb? = null
    override val webService = api

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>> = flow {
        OneCall.enqueue(
            id,
            errorParser::convertGenericError,
            webService::getDetailMovie,
            onEmit = { emit(it) }
        )
    }.flowOn(Dispatchers.IO)

}