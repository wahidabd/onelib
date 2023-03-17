package com.wahidabd.onelibrary.domain.movie

import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import io.reactivex.rxjava3.core.Single

interface MovieUseCase {
    fun getMovies(): Single<Pair<List<Movie>, List<Movie>>>
    fun getDetailMovie(id: Int): Single<Pair<MovieDetail, List<Cast>>>
}