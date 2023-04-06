package com.wahidabd.onelibrary.data.movie.remote

import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

    @GET("movie/popular")
    fun getPopularMovie(): Single<Response<MovieDataResponse<MovieResultResponse>>>

    @GET("movie/popular")
    fun getPaging(
        @Query("page") page: Int
    ): Single<Response<MovieDataResponse<MovieResultResponse>>>

    @GET("movie/upcoming")
    fun getUpcomingMovie(): Single<Response<MovieDataResponse<MovieResultResponse>>>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int
    ): Single<Response<MovieDetailResultResponse>>

    @GET("movie/{id}/credits")
    fun getCast(
        @Path("id") id: Int
    ): Single<Response<CastDataResponse<CastResultResponse>>>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<MovieDataResponse<MovieResultResponse>>

}