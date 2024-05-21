package com.wahidabd.onelibrary.data.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.firebase.OneFirebaseAuth
import com.wahidabd.onelibrary.data.firebase.auth.model.FirebaseAuthRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseAuthDataStore : OneFirebaseAuth, FirebaseAuthRepository {

    override val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun login(body: FirebaseAuthRequest): Flow<Resource<FirebaseUser>> {
        return callbackFlow {
            signIn(body.email, body.password) { body ->
                trySend(body)
            }

            awaitClose(this::close)
        }
    }

    override suspend fun register(body: FirebaseAuthRequest): Flow<Resource<FirebaseUser>> {
        return callbackFlow {
            signUp(body.email, body.password) { body ->
                trySend(body)
            }
            awaitClose(this::close)
        }
    }

}