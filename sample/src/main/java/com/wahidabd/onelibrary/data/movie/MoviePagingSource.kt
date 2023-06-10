//package com.wahidabd.onelibrary.data.movie
//
//import androidx.paging.PagingState
//import androidx.paging.rxjava3.RxPagingSource
//import com.wahidabd.library.utils.rx.operators.getSingleApiError
//import com.wahidabd.library.utils.rx.paging.apiToLoadResult
//import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
//import com.wahidabd.onelibrary.data.movie.model.wrapper.MovieDataResponse
//import com.wahidabd.onelibrary.data.movie.remote.MovieApi
//import io.reactivex.rxjava3.core.Single
//
//
///**
// * Created by Wahid on 4/6/2023.
// * Github github.com/wahidabd.
// */
//
//
//class MoviePagingSource(
//    private val api: MovieApi
//) : RxPagingSource<Int, MovieResultResponse>(){
//
//    override fun getRefreshKey(state: PagingState<Int, MovieResultResponse>): Int? {
//        return state.anchorPosition?.let { position ->
//            state.closestPageToPosition(position)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
//        }
//    }
//
//    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieResultResponse>> {
//        val position = params.key ?: 1
//
//        return api.getPaging(position)
//            .lift(getSingleApiError())
//            .map { apiToLoadResult(it.results, position) }
//            .onErrorReturn { LoadResult.Error(it) }
//    }
//}