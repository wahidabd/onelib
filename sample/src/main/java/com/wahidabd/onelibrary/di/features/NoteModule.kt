package com.wahidabd.onelibrary.di.features

import com.wahidabd.onelibrary.data.AppDatabase
import com.wahidabd.onelibrary.data.note.NoteDataStore
import com.wahidabd.onelibrary.data.note.NoteRepository
import com.wahidabd.onelibrary.domain.note.NoteInteractor
import com.wahidabd.onelibrary.domain.note.NoteUseCase
import com.wahidabd.onelibrary.presentation.rxdatabase.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


val noteModule = module {

    single {
        val db: AppDatabase = get()
        return@single db.noteDao()
    }

    single<NoteRepository> { NoteDataStore(get()) }
    single<NoteUseCase> { NoteInteractor(get()) }
    viewModel { NoteViewModel(get(), get()) }

}