package com.wahidabd.onelibrary.data.movie

import androidx.paging.PagingData
import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MoviePagingResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository : BaseRepository {

    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>>
    suspend fun getMovies(): Flow<PagingData<MovieResultResponse>>

}