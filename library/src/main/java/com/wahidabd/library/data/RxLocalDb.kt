package com.wahidabd.library.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.wahidabd.library.base.Model
import io.reactivex.Single


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

interface RxLocalDb<T: Model> : LocalDb {

    @Insert(onConflict = 1)
    abstract fun save(vararg response: T)

    @Update
    abstract fun update(vararg response: T)

    abstract fun get(intId: Int? = null): Single<T>

    abstract fun getList(): Single<List<T>>

    @Delete
    abstract fun remove(vararg response: T)

}