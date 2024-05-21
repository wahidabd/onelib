package com.wahidabd.onelibrary.presentation.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.storage.FirebaseStorageUseCase
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageData
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageParam
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseStorageViewModel(
    private val useCase: FirebaseStorageUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Resource<Boolean>>()
    val state: LiveData<Resource<Boolean>> get() = _state

    private val _list = MutableLiveData<Resource<List<StorageData>>>()
    val list: LiveData<Resource<List<StorageData>>> get() = _list


    fun getFiles() {
        viewModelScope.launch {
            useCase.getFiles().collectLatest {
                _list.value = it
            }
        }
    }

    fun addFile(body: StorageParam) {
        viewModelScope.launch {
            useCase.uploadFile(body).collectLatest {
                _state.value = it
            }
        }
    }

    fun removeFile(id: String){
        viewModelScope.launch {
            useCase.deleteFile(id).collectLatest {
                _state.value = it
            }
        }
    }
}