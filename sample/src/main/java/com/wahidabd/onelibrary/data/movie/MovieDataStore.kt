package com.wahidabd.onelibrary.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.esafirm.imagepicker.helper.state.asSingleEvent
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.ErrorParses
import com.wahidabd.library.utils.coroutine.SafeCall
import com.wahidabd.library.utils.rx.operators.getSingleApiError
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.max

class MovieDataStore(
    api: MovieApi,
    private val paging: MoviePagingSource
) : MovieRepository {

    override val dbService: LocalDb? = null
    override val webService = api

    override fun getPopularMovie(): Single<MovieDataResponse<MovieResultResponse>> =
        webService.getPopularMovie()
            .lift(getSingleApiError())
            .map { it }

    override fun getUpcomingMovie(): Single<MovieDataResponse<MovieResultResponse>> =
        webService.getUpcomingMovie()
            .lift(getSingleApiError())
            .map { it }

    override fun getDetailMovie(id: Int): Single<MovieDetailResultResponse> =
        webService.getDetailMovie(id)
            .lift(getSingleApiError())
            .map { it }

    override fun getCast(id: Int): Single<CastDataResponse<CastResultResponse>> =
        webService.getCast(id)
            .lift(getSingleApiError())
            .map { it }

    override fun getPaging(): Flowable<PagingData<MovieResultResponse>> = Pager(
        config = PagingConfig(
            pageSize = 5,
            maxSize = 20,
            enablePlaceholders = false
        ), pagingSourceFactory = {MoviePagingSource(webService)}
    ).flowable

    override suspend fun getNowPlaying(): Flow<MovieDataResponse<MovieResultResponse>> = flow {
        emit(webService.getNowPlaying().body()!!)
    }


}