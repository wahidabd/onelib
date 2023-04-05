package com.wahidabd.onelibrary.data.note

import com.wahidabd.library.data.WebApi
import com.wahidabd.onelibrary.data.note.local.NoteDao
import com.wahidabd.onelibrary.data.note.local.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class NoteDataStore(private val db: NoteDao) : NoteRepository {

    override val dbService = db
    override val webService: WebApi? = null

    override fun addNote(noteEntity: NoteEntity): Completable =
        Completable.fromAction {
            dbService.save(noteEntity)
        }

    override fun getNotes(): Single<List<NoteEntity>> =
        dbService.getList()

    override fun removeNote(id: Int): Completable =
        Completable.fromAction {
            dbService.remove(id)
        }

}