package com.wahidabd.onelibrary.di.features

import com.wahidabd.onelibrary.data.firebase.FirebaseDataSource
import com.wahidabd.onelibrary.data.firebase.FirebaseRepository
import com.wahidabd.onelibrary.domain.firebase.FirestoreInteractor
import com.wahidabd.onelibrary.domain.firebase.FirestoreUseCase
import com.wahidabd.onelibrary.presentation.firestore.FirestoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


val firebaseModule = module {
    single<FirebaseRepository> { FirebaseDataSource() }
    single<FirestoreUseCase> { FirestoreInteractor(get()) }
    viewModel { FirestoreViewModel(get()) }
}