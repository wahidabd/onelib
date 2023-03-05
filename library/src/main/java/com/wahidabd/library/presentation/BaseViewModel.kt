package com.wahidabd.library.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private lateinit var disposable: CompositeDisposable

    override fun onCleared() {
        super.onCleared()
        if (disposable.isDisposed){
            disposable.dispose()
        }
    }

}