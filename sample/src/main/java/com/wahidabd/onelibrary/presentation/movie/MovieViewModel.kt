package com.wahidabd.onelibrary.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.observerScheduler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel(){
//
//    private val _movies = MutableLiveData<Resource<Pair<List<Movie>, List<Movie>>>>()
//    val movies: LiveData<Resource<Pair<List<Movie>, List<Movie>>>> get() = _movies

    private val _detail = MutableLiveData<Resource<MovieDetailResultResponse>> ()
    fun detail(id: Int): LiveData<Resource<MovieDetail>> =
        movieUseCase.getDetailMovie(id).asLiveData()



//    fun getMovies(){
//        _movies.value = Resource.loading()
//
//        movieUseCase.getMovies()
//            .compose(singleScheduler())
//            .subscribe({
//                _movies.value = Resource.success(it)
//            }, { genericErrorHandler(it, _movies) })
//            .addTo(disposable)
//    }

}