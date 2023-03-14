package com.wahidabd.onelibrary.domain.movie

import com.wahidabd.onelibrary.data.movie.MovieRepository
import com.wahidabd.onelibrary.domain.movie.mapper.toDomain
import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import io.reactivex.rxjava3.core.Single

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    override fun getMovies(): Single<Pair<List<Movie>, List<Movie>>> =
        Single.zip(repository.getPopularMovie(), repository.getUpcomingMovie())
        { popular, upcoming ->
            Pair(
                popular.results.map {
                    it.toDomain()
                },
                upcoming.results.map {
                    it.toDomain()
                }
            )
        }

    override fun getDetailMovie(id: Int): Single<Pair<MovieDetail, List<Cast>>> =
        Single.zip(
            repository.getDetailMovie(id),
            repository.getCast(id)
        ) { movie, cast ->
            Pair(
                movie.toDomain(),
                cast.cast.map {
                    it.toDomain()
                }
            )
        }


}