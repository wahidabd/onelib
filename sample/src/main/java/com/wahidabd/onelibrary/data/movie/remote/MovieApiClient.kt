package com.wahidabd.onelibrary.data.movie.remote

import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MoviePagingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviePagingResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): Response<MovieDetailResultResponse>

}