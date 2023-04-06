package com.wahidabd.onelibrary.domain.movie

import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Single<Pair<List<Movie>, List<Movie>>>
    fun getDetailMovie(id: Int): Single<Pair<MovieDetail, List<Cast>>>
    fun getPaging(): Flowable<PagingData<Movie>>
}