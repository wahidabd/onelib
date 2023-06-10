package com.wahidabd.onelibrary.data.movie.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.CastDataResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MovieApi(private val apiClient: MovieApiClient) : WebApi, MovieApiClient {
//    override fun getPopularMovie(): Single<Response<MovieDataResponse<MovieResultResponse>>> =
//        apiClient.getPopularMovie()
//
//    override fun getPaging(page: Int): Single<Response<MovieDataResponse<MovieResultResponse>>> =
//        apiClient.getPaging(page)
//
//    override fun getUpcomingMovie(): Single<Response<MovieDataResponse<MovieResultResponse>>> =
//        apiClient.getUpcomingMovie()

    override suspend fun getDetailMovie(id: Int): Response<MovieDetailResultResponse> =
        apiClient.getDetailMovie(id)

//    override fun getCast(id: Int): Response<CastDataResponse<CastResultResponse>> =
//        apiClient.getCast(id)
//
//    override suspend fun getNowPlaying(): Response<MovieDataResponse<MovieResultResponse>> =
//        apiClient.getNowPlaying()

}