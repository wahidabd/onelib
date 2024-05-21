package com.wahidabd.onelibrary.domain.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuthParam
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


interface FirebaseAuthUseCase {
    suspend fun login(body: FirebaseAuthParam): Flow<Resource<FirebaseUser>>
    suspend fun register(body: FirebaseAuthParam): Flow<Resource<FirebaseUser>>
}