package com.wahidabd.onelibrary.data.movie

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository : BaseRepository {

    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>>

}