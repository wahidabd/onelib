package com.wahidabd.onelibrary.data.note.local

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
abstract class NoteDao  {

    @Query("SELECT * FROM note")
    abstract fun getList(): Single<List<NoteEntity>>

    @Query("DELETE FROM note WHERE id = :intId")
    abstract fun remove(intId: Int?)

    @Query("SELECT * FROM note WHERE id = :intId")
    abstract fun get(intId: Int?): Single<NoteEntity>

}