package com.wahidabd.onelibrary.domain.movie

import androidx.paging.PagingData
import androidx.paging.map
import androidx.work.ListenableWorker.Result.Success
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.EmitResource
import com.wahidabd.library.utils.coroutine.NetworkBoundResource
import com.wahidabd.onelibrary.data.movie.MovieRepository
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
import com.wahidabd.onelibrary.domain.movie.mapper.toDomain
import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    //    override fun getMovies(): Single<Pair<List<Movie>, List<Movie>>> =
//        Single.zip(repository.getPopularMovie(), repository.getUpcomingMovie())
//        { popular, upcoming ->
//            Pair(
//                popular.results.map {
//                    it.toDomain()
//                },
//                upcoming.results.map {
//                    it.toDomain()
//                }
//            )
//        }
//
//    override fun getDetailMovie(id: Int): Observable<Pair<MovieDetail, List<Cast>>> =
//        Observable.zip(
//            repository.getDetailMovie(id),
//            repository.getCast(id)
//        ) { movie, cast ->
//            Pair(
//                movie.toDomain(),
//                cast.cast.map {
//                    it.toDomain()
//                }
//            )
//        }
//
//    override fun getPaging(): Flowable<PagingData<Movie>> =
//        repository.getPaging().map {
//            it.map { data ->
//                data.toDomain()
//            }
//        }
    override fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return object : NetworkBoundResource<MovieDetail, MovieDetailResultResponse>() {

            override suspend fun createCall(): Flow<Resource<MovieDetailResultResponse>> {
                return repository.getDetailMovie(id)
            }

            override suspend fun saveCallRequest(data: MovieDetailResultResponse) {
                data.toDomain()
            }

            override fun shouldFetch(data: MovieDetail?): Boolean {
                return true
            }
        }.asFlow()
    }

}
