package com.wahidabd.onelibrary.presentation.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.domain.movie.model.Movie
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class PagingViewModel(
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _paging = MutableLiveData<PagingData<Movie>>()
    val paging: LiveData<PagingData<Movie>> get() = _paging

    fun paging() {
        movieUseCase.getPaging()
            .subscribe {
                _paging.value = it
            }
            .addTo(disposable)
    }

}