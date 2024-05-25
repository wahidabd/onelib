package com.wahidabd.onelibrary.presentation.localdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.onelibrary.domain.note.NoteUseCase
import com.wahidabd.onelibrary.domain.note.model.Note
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


class NoteViewModel(
    private val useCase: NoteUseCase,
) : ViewModel() {

    private val _getNotes = MutableLiveData<List<Note>>()
    val getNotes: LiveData<List<Note>> get() = _getNotes

    private val _getNote = MutableLiveData<Note>()
    val getNote: LiveData<Note> get() = _getNote


    fun addNote(data: Note) {
        viewModelScope.launch {
            useCase.save(data)
        }
    }

    fun update(data: Note){
        viewModelScope.launch {
            useCase.update(data)
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            useCase.getList().collectLatest {
                _getNotes.postValue(it)
            }
        }
    }

    fun getNote(id: Int){
        viewModelScope.launch {
            useCase.get(id).collectLatest {
                _getNote.postValue(it)
            }
        }
    }

    fun remove(data: Note) {
        viewModelScope.launch {
            useCase.remove(data)
        }
    }
}