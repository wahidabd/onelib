package com.wahidabd.onelibrary.data.movie

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.ErrorParses
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieDataStore(
    api: MovieApi,
    private val errorParser: ErrorParses,
) : MovieRepository {

    override val dbService: LocalDb? = null
    override val webService = api

//    override fun getPopularMovie(): Single<MovieDataResponse<MovieResultResponse>> =
//        webService.getPopularMovie()
//            .lift(getSingleApiError())
//            .map { it }
//
//    override fun getUpcomingMovie(): Single<MovieDataResponse<MovieResultResponse>> =
//        webService.getUpcomingMovie()
//            .lift(getSingleApiError())
//            .map { it }

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>> = flow {
        enqueue(
            id,
            errorParser::convertGenericError,
            webService::getDetailMovie,
            onEmit = {
                emit(it)
            }
        )
    }.flowOn(Dispatchers.IO)

//    override fun getCast(id: Int): Flow<Resource<CastDataResponse<CastResultResponse>>> = flow {
//        enqueue(
//            id,
//            errorParser::convertGenericError,
//            webService::getCast,
//            onEmit = {
//                emit(it)
//            }
//        )
//    }.flowOn(Dispatchers.IO)
//
//    override fun getPaging(): Flowable<PagingData<MovieResultResponse>> = Pager(
//        config = PagingConfig(
//            pageSize = 5,
//            maxSize = 20,
//            enablePlaceholders = false
//        ), pagingSourceFactory = {MoviePagingSource(webService)}
//    ).flowable
//
//    override suspend fun getNowPlaying(): Flow<MovieDataResponse<MovieResultResponse>> = flow {
//        emit(webService.getNowPlaying().body()!!)
//    }


}