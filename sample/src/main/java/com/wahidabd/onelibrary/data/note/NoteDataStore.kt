package com.wahidabd.onelibrary.data.note

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.onelibrary.data.note.local.NoteDao
import com.wahidabd.onelibrary.data.note.local.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class NoteDataStore(private val db: NoteDao) : NoteRepository {


    override fun addNote(noteEntity: NoteEntity): Completable =
        Completable.fromAction {
        }

    override fun getNotes(): Single<List<NoteEntity>> =
        db.getList()

    override fun removeNote(id: Int): Completable =
        Completable.fromAction {
            db.remove(id)
        }

}