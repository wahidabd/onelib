package com.wahidabd.onelibrary.data.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.auth.model.FirebaseAuthRequest
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


interface FirebaseAuthRepository {

    suspend fun login(body: FirebaseAuthRequest): Flow<Resource<FirebaseUser>>
    suspend fun register(body: FirebaseAuthRequest): Flow<Resource<FirebaseUser>>
    suspend fun resetPassword(email: String): Flow<Resource<Boolean>>
    suspend fun signOut(): Flow<Resource<Boolean>>
}