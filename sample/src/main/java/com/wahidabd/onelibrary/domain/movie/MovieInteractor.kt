package com.wahidabd.onelibrary.domain.movie

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.onelibrary.data.movie.MovieRepository
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.domain.movie.mapper.toDomain
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return object : InternetBoundResource<MovieDetail, MovieDetailResultResponse>() {

            override suspend fun createCall(): Flow<Resource<MovieDetailResultResponse>> {
                return repository.getDetailMovie(id)
            }

            override suspend fun saveCallRequest(data: MovieDetailResultResponse): MovieDetail {
                return data.toDomain()
            }
        }.asFlow()
    }

}
