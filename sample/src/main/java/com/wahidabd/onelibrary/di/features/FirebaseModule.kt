package com.wahidabd.onelibrary.di.features

import com.wahidabd.onelibrary.data.firebase.firestore.OneFirebaseDataSource
import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseRepository
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeDataSourceOne
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeRepository
import com.wahidabd.onelibrary.domain.firebase.firestore.FirestoreInteractor
import com.wahidabd.onelibrary.domain.firebase.firestore.FirestoreUseCase
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeInteractor
import com.wahidabd.onelibrary.domain.firebase.realtime.RealtimeUseCase
import com.wahidabd.onelibrary.presentation.firestore.FirestoreViewModel
import com.wahidabd.onelibrary.presentation.realtime.RealtimeViewModel
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