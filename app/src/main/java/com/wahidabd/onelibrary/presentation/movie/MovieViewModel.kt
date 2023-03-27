package com.wahidabd.onelibrary.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.domain.movie.model.Movie
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable){

    private val _movies = MutableLiveData<Resource<Pair<List<Movie>, List<Movie>>>>()
    val movies: LiveData<Resource<Pair<List<Movie>, List<Movie>>>> get() = _movies

    init {
        _movies.value = Resource.default()
    }

    fun getMovies(){
        _movies.value = Resource.loading()

        movieUseCase.getMovies()
            .compose(singleScheduler())
            .subscribe({
                _movies.value = Resource.success(it)
            }, { genericErrorHandler(it, _movies) })
            .addTo(disposable)
    }

}