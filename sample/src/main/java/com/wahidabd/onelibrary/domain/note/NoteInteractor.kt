package com.wahidabd.onelibrary.domain.note

import com.wahidabd.onelibrary.data.note.NoteRepository
import com.wahidabd.onelibrary.domain.note.mapper.toDomain
import com.wahidabd.onelibrary.domain.note.mapper.toEntity
import com.wahidabd.onelibrary.domain.note.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


class NoteInteractor(private val repository: NoteRepository) : NoteUseCase {

    override suspend fun save(data: Note) {
        return repository.save(data.toEntity())
    }

    override suspend fun update(data: Note) {
        return repository.update(data.toEntity())
    }

    override suspend fun get(id: Int): Flow<Note> {
        return repository.get(id).map { it.toDomain() }
    }

    override suspend fun getList(): Flow<List<Note>> {
        return repository.getList().map { it.map { data -> data.toDomain() } }
    }

    override suspend fun remove(data: Note) {
        return repository.remove(data.toEntity())
    }
}