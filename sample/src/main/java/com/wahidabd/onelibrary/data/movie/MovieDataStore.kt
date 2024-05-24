package com.wahidabd.onelibrary.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.OneCall
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MoviePagingResponse
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

    override suspend fun getMovies(): Flow<PagingData<MovieResultResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingDataStore(webService) }
        ).flow
    }

}