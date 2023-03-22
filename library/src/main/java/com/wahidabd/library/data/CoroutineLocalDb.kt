package com.wahidabd.library.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.wahidabd.library.base.Model
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 3/22/2023.
 * Github wahidabd.
 */

interface CoroutineLocalDb<T : Model> : LocalDb {

    @Insert(onConflict = 1)
    abstract suspend fun save(vararg response: T)

    @Update
    abstract suspend fun update(vararg response: T)

    abstract fun get(intId: Int? = null): Flow<T>

    abstract fun getList(): Flow<List<T>>

    @Delete
    abstract suspend fun remove(vararg response: T)

}