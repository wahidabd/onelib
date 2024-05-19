package com.wahidabd.onelibrary.data.movie.remote

import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): Response<MovieDetailResultResponse>

}