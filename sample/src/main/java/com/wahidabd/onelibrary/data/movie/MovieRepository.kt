package com.wahidabd.onelibrary.data.movie

import androidx.paging.PagingData
import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface MovieRepository : BaseRepository {
//
//    fun getPopularMovie(): Single<MovieDataResponse<MovieResultResponse>>
//    fun getUpcomingMovie(): Single<MovieDataResponse<MovieResultResponse>>
    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>>
//    fun getCast(id: Int): Flow<Resource<CastDataResponse<CastResultResponse>>>
//    fun getPaging(): Flowable<PagingData<MovieResultResponse>>
//
//    suspend fun getNowPlaying(): Flow<MovieDataResponse<MovieResultResponse>>

}