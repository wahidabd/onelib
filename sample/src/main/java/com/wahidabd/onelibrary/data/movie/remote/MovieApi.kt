package com.wahidabd.onelibrary.data.movie.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MoviePagingResponse
import retrofit2.Response

class MovieApi(private val apiClient: MovieApiClient) : WebApi, MovieApiClient {
    override suspend fun getMovies(page: Int): MoviePagingResponse {
        return apiClient.getMovies(page)
    }

    override suspend fun getDetailMovie(id: Int): Response<MovieDetailResultResponse> =
        apiClient.getDetailMovie(id)


}