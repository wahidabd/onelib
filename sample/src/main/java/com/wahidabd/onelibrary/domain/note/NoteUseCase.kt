package com.wahidabd.onelibrary.domain.note

import com.wahidabd.onelibrary.domain.note.model.Note
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


interface NoteUseCase {

    suspend fun save(data: Note)
    suspend fun update(data: Note)
    suspend fun get(id: Int): Flow<Note>
    suspend fun getList(): Flow<List<Note>>
    suspend fun remove(data: Note)

}