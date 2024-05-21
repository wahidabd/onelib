package com.wahidabd.onelibrary.presentation

import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.onelibrary.di.apiModule
import com.wahidabd.onelibrary.di.coroutineModule
import com.wahidabd.onelibrary.di.dbModule
import com.wahidabd.onelibrary.di.features.firebaseAuth
import com.wahidabd.onelibrary.di.features.firebaseModule
import com.wahidabd.onelibrary.di.features.firebaseStorage
import com.wahidabd.onelibrary.di.features.movieModule
import com.wahidabd.onelibrary.di.features.noteModule
import com.wahidabd.onelibrary.di.features.realtimeModule
import com.wahidabd.onelibrary.di.rxModule
import org.koin.core.module.Module
import timber.log.Timber

class App : BaseApplication() {

    override fun getDefineModule(): List<Module> =
        listOf(
            apiModule,
            dbModule,
            rxModule,
            coroutineModule,
            movieModule,
            noteModule,
            firebaseModule,
            realtimeModule,
            firebaseAuth,
            firebaseStorage
        )

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }
}