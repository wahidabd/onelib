package com.wahidabd.onelibrary.data.movie

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.utils.rx.operators.getSingleApiError
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import io.reactivex.rxjava3.core.Single

class MovieDataStore(
    api: MovieApi
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

}