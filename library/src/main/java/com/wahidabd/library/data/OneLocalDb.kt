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


/**
 * Interface representing a local database for handling CRUD operations for a specific model type.
 *
 * @param T the type of the model that extends from the base Model class.
 * @param K the type of the key that uniquely identifies a model instance.
 */
interface OneLocalDb<T : Model?, K : Any> : LocalDb {

    /**
     * Saves one or more model instances into the local database.
     * If a conflict occurs (e.g., primary key conflict), the existing entry will be replaced.
     *
     * @param response the model instances to save.
     */
    @Insert(onConflict = 1)
    suspend fun save(vararg response: T)

    /**
     * Updates one or more model instances in the local database.
     *
     * @param response the model instances to update.
     */
    @Update
    suspend fun update(vararg response: T)

    /**
     * Retrieves a model instance from the local database by its unique identifier.
     * If no identifier is provided, the method might return a default or null value.
     *
     * @param id the unique identifier of the model instance to retrieve.
     * @return a Flow emitting the model instance.
     */
    fun get(id: K? = null): Flow<T>

    /**
     * Retrieves a list of all model instances from the local database.
     *
     * @return a Flow emitting the list of model instances.
     */
    fun getList(): Flow<List<T>>

    /**
     * Removes one or more model instances from the local database.
     *
     * @param response the model instances to remove.
     */
    @Delete
    suspend fun remove(vararg response: T)

}