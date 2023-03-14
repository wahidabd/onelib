package com.wahidabd.onelibrary.data.movie

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import io.reactivex.rxjava3.core.Single

interface MovieRepository : BaseRepository {

    fun getPopularMovie(): Single<MovieDataResponse<MovieResultResponse>>
    fun getUpcomingMovie(): Single<MovieDataResponse<MovieResultResponse>>
    fun getDetailMovie(id: Int): Single<MovieDetailResultResponse>
    fun getCast(id: Int): Single<CastDataResponse<CastResultResponse>>

}