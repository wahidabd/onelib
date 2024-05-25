package com.wahidabd.onelibrary.data.note

import com.wahidabd.onelibrary.data.note.local.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun save(data: NoteEntity)
    suspend fun update(data: NoteEntity)
    suspend fun get(id: Int): Flow<NoteEntity>
    suspend fun getList(): Flow<List<NoteEntity>>
    suspend fun remove(data: NoteEntity)
}