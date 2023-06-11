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

    override suspend fun getDetailMovie(id: Int): Response<MovieDetailResultResponse> =
        apiClient.getDetailMovie(id)


}