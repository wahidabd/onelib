package com.wahidabd.onelibrary.di.features

import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseDataSource
import com.wahidabd.onelibrary.data.firebase.firestore.FirebaseRepository
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeDataSource
import com.wahidabd.onelibrary.data.firebase.raltime.RealtimeRepository
import com.wahidabd.onelibrary.domain.firebase.FirestoreInteractor
import com.wahidabd.onelibrary.domain.firebase.FirestoreUseCase
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
    single<FirebaseRepository> { FirebaseDataSource() }
    single<FirestoreUseCase> { FirestoreInteractor(get()) }
    viewModel { FirestoreViewModel(get()) }
}

val realtimeModule = module {
    single<RealtimeRepository> { RealtimeDataSource() }
    single<RealtimeUseCase> { RealtimeInteractor(get()) }
    viewModel { RealtimeViewModel(get()) }
}