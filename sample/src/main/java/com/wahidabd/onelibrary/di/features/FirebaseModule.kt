package com.wahidabd.onelibrary.di.features

import com.wahidabd.onelibrary.data.firebase.auth.FirebaseAuthDataStore
import com.wahidabd.onelibrary.data.firebase.auth.FirebaseAuthRepository
import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseRepository
import com.wahidabd.onelibrary.data.firebase.firestore.OneFirebaseDataSource
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeDataSourceOne
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeRepository
import com.wahidabd.onelibrary.data.firebase.storage.FirebaseStorageDataStore
import com.wahidabd.onelibrary.data.firebase.storage.FirebaseStorageRepository
import com.wahidabd.onelibrary.domain.firebase.auth.FirebaseAuthInteractor
import com.wahidabd.onelibrary.domain.firebase.auth.FirebaseAuthUseCase
import com.wahidabd.onelibrary.domain.firebase.firestore.FirestoreInteractor
import com.wahidabd.onelibrary.domain.firebase.firestore.FirestoreUseCase
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeInteractor
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeUseCase
import com.wahidabd.onelibrary.domain.firebase.storage.FirebaseStorageInteractor
import com.wahidabd.onelibrary.domain.firebase.storage.FirebaseStorageUseCase
import com.wahidabd.onelibrary.presentation.firebaseauth.FirebaseAuthViewModel
import com.wahidabd.onelibrary.presentation.firestore.FirestoreViewModel
import com.wahidabd.onelibrary.presentation.realtime.RealtimeViewModel
import com.wahidabd.onelibrary.presentation.storage.FirebaseStorageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


val firebaseModule = module {
    single<FirebaseRepository> { OneFirebaseDataSource() }
    single<FirestoreUseCase> { FirestoreInteractor(get()) }
    viewModel { FirestoreViewModel(get()) }
}

val realtimeModule = module {
    single<RealtimeRepository> { RealtimeDataSourceOne() }
    single<RealtimeUseCase> { RealtimeInteractor(get()) }
    viewModel { RealtimeViewModel(get()) }
}

val firebaseAuth = module {
    single<FirebaseAuthRepository> { FirebaseAuthDataStore() }
    single<FirebaseAuthUseCase> { FirebaseAuthInteractor(get()) }
    viewModel { FirebaseAuthViewModel(get()) }
}

val firebaseStorage = module {
    single<FirebaseStorageRepository> { FirebaseStorageDataStore() }
    single<FirebaseStorageUseCase> { FirebaseStorageInteractor(get()) }
    viewModel { FirebaseStorageViewModel(get()) }
}