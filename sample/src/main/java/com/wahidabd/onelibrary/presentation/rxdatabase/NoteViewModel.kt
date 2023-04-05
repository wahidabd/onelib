package com.wahidabd.onelibrary.presentation.rxdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.completableScheduler
import com.wahidabd.onelibrary.domain.note.NoteUseCase
import com.wahidabd.onelibrary.domain.note.model.Note
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


class NoteViewModel(
    private val useCase: NoteUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _getNotes = MutableLiveData<Resource<List<Note>>>()
    val getNotes: LiveData<Resource<List<Note>>> get() = _getNotes

    private val _addNote = MutableLiveData<Resource<Unit>>()
    val addNote: LiveData<Resource<Unit>> get() = _addNote

    private val _removeNote = MutableLiveData<Resource<Unit>>()
    val removeNote: LiveData<Resource<Unit>> get() = _removeNote

    init {
        _getNotes.value = Resource.default()
        _addNote.value = Resource.default()
        _removeNote.value = Resource.default()
    }

    fun addNote(note: Note){
        _addNote.value = Resource.loading()
        useCase.addNote(note)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _addNote.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _addNote) })
            .addTo(disposable)

    }

}