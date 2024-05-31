package com.wahidabd.onelibrary.domain.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.exts.oneMap
import com.wahidabd.onelibrary.data.movie.MovieRepository
import com.wahidabd.onelibrary.domain.movie.mapper.toDomain
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return repository.getDetailMovie(id).map { resource ->
            resource.oneMap { response -> response.toDomain() }
        }
    }

    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return repository.getMovies().map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

}
