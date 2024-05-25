package com.wahidabd.onelibrary.data.note.local

import androidx.room.Dao
import androidx.room.Query
import com.wahidabd.library.data.OneLocalDb
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao : OneLocalDb<NoteEntity, Int> {

    @Query("SELECT * FROM note")
    abstract override fun getList(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    abstract override fun get(id: Int?): Flow<NoteEntity>
}