package com.wahidabd.onelibrary.data.note.local

import androidx.room.Dao
import androidx.room.Query
import com.wahidabd.library.data.RxLocalDb
import io.reactivex.rxjava3.core.Single

@Dao
abstract class NoteDao : RxLocalDb<NoteEntity> {

    @Query("SELECT * FROM note")
    abstract override fun getList(): Single<List<NoteEntity>>

    @Query("DELETE FROM note WHERE id = :intId")
    abstract override fun remove(intId: Int?)

    @Query("SELECT * FROM note WHERE id = :intId")
    abstract override fun get(intId: Int?): Single<NoteEntity>

}