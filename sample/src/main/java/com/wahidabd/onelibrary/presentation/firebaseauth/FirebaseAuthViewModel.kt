package com.wahidabd.onelibrary.presentation.firebaseauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.auth.FirebaseAuthUseCase
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuthParam
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseAuthViewModel(
    private val viewModel: FirebaseAuthUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Resource<FirebaseUser>>()
    val user: LiveData<Resource<FirebaseUser>> get() = _user

    fun login(body: FirebaseAuthParam) {
        viewModelScope.launch {
            viewModel.login(body).collectLatest {
                _user.value = it
            }
        }
    }

    fun register(body: FirebaseAuthParam) {
        viewModelScope.launch {
            viewModel.register(body).collectLatest {
                _user.value = it
            }
        }
    }

}