package com.wahidabd.onelibrary.data.note

import com.wahidabd.onelibrary.data.note.local.NoteDao
import com.wahidabd.onelibrary.data.note.local.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteDataStore(private val db: NoteDao) : NoteRepository {

    override suspend fun save(data: NoteEntity) {
        db.save(data)
    }

    override suspend fun update(data: NoteEntity) {
        db.update(data)
    }

    override suspend fun get(id: Int): Flow<NoteEntity> {
        return db.get(id)
    }

    override suspend fun getList(): Flow<List<NoteEntity>> {
        return db.getList()
    }

    override suspend fun remove(data: NoteEntity) {
        db.remove(data)
    }

}