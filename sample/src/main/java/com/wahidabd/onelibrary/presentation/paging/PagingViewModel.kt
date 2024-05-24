package com.wahidabd.onelibrary.presentation.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.domain.movie.model.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class PagingViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _paging = MutableLiveData<PagingData<Movie>>()
    val paging: LiveData<PagingData<Movie>> get() = _paging

    fun paging() {
        viewModelScope.launch {
            movieUseCase.getMovies().collectLatest {
                _paging.value = it
            }
        }
    }

}