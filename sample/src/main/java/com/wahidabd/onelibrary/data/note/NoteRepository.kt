package com.wahidabd.onelibrary.data.note

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.onelibrary.data.note.local.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NoteRepository : BaseRepository {

    fun addNote(noteEntity: NoteEntity): Completable

    fun getNotes(): Single<List<NoteEntity>>

    fun removeNote(id: Int): Completable

}