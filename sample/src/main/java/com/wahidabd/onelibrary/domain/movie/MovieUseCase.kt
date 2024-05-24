package com.wahidabd.onelibrary.domain.movie

import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
    suspend fun getMovies(): Flow<PagingData<Movie>>
}