package com.wahidabd.onelibrary.domain.movie

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
}