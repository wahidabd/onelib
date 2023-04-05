package com.wahidabd.onelibrary.domain.note

import com.wahidabd.onelibrary.domain.note.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


interface NoteUseCase {

    fun addNote(note: Note): Completable
    fun getNotes(): Single<List<Note>>
    fun remove(id: Int): Completable

}