package com.wahidabd.onelibrary.domain.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource
import com.wahidabd.onelibrary.data.firebase.auth.FirebaseAuthRepository
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuthParam
import com.wahidabd.onelibrary.domain.firebase.auth.model.mapper.toRequest
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


class FirebaseAuthInteractor(
    private val repository: FirebaseAuthRepository
) : FirebaseAuthUseCase {
    override suspend fun login(body: FirebaseAuthParam): Flow<Resource<FirebaseUser>> {
        return repository.login(body.toRequest())
    }

    override suspend fun register(body: FirebaseAuthParam): Flow<Resource<FirebaseUser>> {
        return repository.register(body.toRequest())
    }
}