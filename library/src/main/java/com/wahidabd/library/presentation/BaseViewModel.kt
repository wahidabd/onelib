package com.wahidabd.library.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(
    val disposable: CompositeDisposable
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed){
            disposable.dispose()
        }
    }

}