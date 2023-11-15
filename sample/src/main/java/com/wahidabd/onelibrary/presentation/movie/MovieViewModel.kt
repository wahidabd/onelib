package com.wahidabd.onelibrary.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _detail = MutableLiveData<Resource<MovieDetail>>()
    val detail: LiveData<Resource<MovieDetail>> get() = _detail

    fun detail(id: Int) {
        viewModelScope.launch {
            movieUseCase.getDetailMovie(id).collectLatest {
                _detail.value = it
                debug { "VIEWMODEL --> $it" }
            }
        }
    }

}