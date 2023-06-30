package com.wahidabd.onelibrary.domain.firebase

import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/27/2023.
 * Github github.com/wahidabd.
 */


interface FirestoreUseCase {
    fun writeData(request: FirestoreParam): Flow<Resource<Boolean>>
    fun getList(): Flow<Resource<List<FirestoreData>>>
    fun getSingle(id: String): Flow<Resource<FirestoreData>>
    fun remove(id: String): Flow<Resource<Boolean>>
}