package com.wahidabd.onelibrary.domain.note

import com.wahidabd.onelibrary.data.note.NoteRepository
import com.wahidabd.onelibrary.domain.note.mapper.toDomain
import com.wahidabd.onelibrary.domain.note.mapper.toEntity
import com.wahidabd.onelibrary.domain.note.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


class NoteInteractor(private val repository: NoteRepository) : NoteUseCase {

    override fun addNote(note: Note): Completable =
        repository.addNote(note.toEntity())

    override fun getNotes(): Single<List<Note>> =
        repository.getNotes().map {
            it.map { noteEntity ->
                noteEntity.toDomain()
            }
        }

    override fun remove(id: Int): Completable =
        repository.removeNote(id)

}