package com.wahidabd.onelibrary.presentation.realtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeData
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeViewModel(
    private val useCase: RealtimeUseCase
) : ViewModel() {

    private val _add = MutableLiveData<Resource<Boolean>>()
    val add: LiveData<Resource<Boolean>> get() = _add

    private val _remove = MutableLiveData<Resource<Boolean>>()
    val remove: LiveData<Resource<Boolean>> get() = _remove

    private val _list = MutableLiveData<Resource<List<RealtimeData>>>()
    val list: LiveData<Resource<List<RealtimeData>>> get() = _list

    private val _edit = MutableLiveData<Resource<Boolean>>()
    val edit: LiveData<Resource<Boolean>> get() = _edit

    private val _data = MutableLiveData<Resource<RealtimeData>>()
    val data: LiveData<Resource<RealtimeData>> get() = _data

    fun add(data: RealtimeParam) {
        viewModelScope.launch {
            useCase.realtimeAdd(data)
                .onEach { _add.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun remove(id: String) {
        viewModelScope.launch {
            useCase.realtimeRemove(id)
                .onEach { _remove.value = it }
                .launchIn(viewModelScope)
        }
    }


    fun list() {
        viewModelScope.launch {
            useCase.realtimeList()
                .onEach { _list.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun edit(id: String) {
        viewModelScope.launch {
            useCase.realtimeEdit(id)
                .onEach { _edit.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun data(id: String) {
        viewModelScope.launch {
            useCase.realtimeData(id)
                .onEach { _data.value = it }
                .launchIn(viewModelScope)
        }
    }
}