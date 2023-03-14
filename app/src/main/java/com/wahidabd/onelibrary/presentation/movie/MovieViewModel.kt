package com.wahidabd.onelibrary.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Result
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

    private val _movies = MutableLiveData<Result<Pair<List<Movie>, List<Movie>>>>()
    val movies: LiveData<Result<Pair<List<Movie>, List<Movie>>>> get() = _movies

    init {
        _movies.value = Result.default()
    }

    fun getMovies(){
        _movies.value = Result.loading()

        movieUseCase.getMovies()
            .compose(singleScheduler())
            .subscribe({
                _movies.value = Result.success(it)
            }, { genericErrorHandler(it, _movies) })
            .addTo(disposable)
    }

}