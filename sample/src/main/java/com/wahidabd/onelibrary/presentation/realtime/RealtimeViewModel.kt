package com.wahidabd.onelibrary.presentation.realtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeData
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeViewModel(
    private val useCase: RealtimeUseCase
) : ViewModel() {

    private val _add = MutableLiveData<Resource<GenericResponse>>()
    val add: LiveData<Resource<GenericResponse>> get() = _add

    private val _remove = MutableLiveData<Resource<GenericResponse>>()
    val remove: LiveData<Resource<GenericResponse>> get() = _remove

    private val _list = MutableLiveData<Resource<List<RealtimeData>>>()
    val list: LiveData<Resource<List<RealtimeData>>> get() = _list

    fun add(data: RealtimeParam){
        useCase.realtimeAdd(data)
            .onEach { _add.value = it }
            .launchIn(viewModelScope)
    }

    fun remove(id: String){
        useCase.realtimeRemove(id)
            .onEach { _remove.value = it }
            .launchIn(viewModelScope)
    }


    fun list(){
        useCase.realtimeList()
            .onEach { _list.value = it }
            .launchIn(viewModelScope)
    }
}