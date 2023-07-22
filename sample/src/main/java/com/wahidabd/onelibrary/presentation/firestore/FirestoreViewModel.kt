package com.wahidabd.onelibrary.presentation.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.handler.GenericResponse
import com.wahidabd.onelibrary.data.firebase.model.firestore.FirestoreRequest
import com.wahidabd.onelibrary.domain.firebase.FirestoreUseCase
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


class FirestoreViewModel(
    private val useCase: FirestoreUseCase
) : ViewModel() {

    private val _add = MutableLiveData<Resource<GenericResponse>>()
    val add: LiveData<Resource<GenericResponse>> get() = _add

    private val _list = MutableLiveData<Resource<List<FirestoreData>>>()
    val list: LiveData<Resource<List<FirestoreData>>> get() = _list

    private val _remove = MutableLiveData<Resource<GenericResponse>>()
    val remove: LiveData<Resource<GenericResponse>> get() = _remove

    private val _data = MutableLiveData<Resource<FirestoreData>>()
    val data: LiveData<Resource<FirestoreData>> get() = _data

    fun add(req: FirestoreParam) {
        useCase.writeData(req)
            .onEach { _add.value = it }
            .launchIn(viewModelScope)
    }

    fun update(req: FirestoreRequest){
        useCase.update(req)
            .onEach { _add.value = it }
            .launchIn(viewModelScope)
    }

    fun list() {
        useCase.getList()
            .onEach { _list.value = it }
            .launchIn(viewModelScope)
    }

    fun data(id: String) {
        useCase.getSingle(id)
            .onEach { _data.value = it }
            .launchIn(viewModelScope)
    }

    fun remove(id: String) {
        useCase.remove(id)
            .onEach { _remove.value = it }
            .launchIn(viewModelScope)
    }
}