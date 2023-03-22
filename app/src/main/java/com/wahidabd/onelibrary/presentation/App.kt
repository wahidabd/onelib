package com.wahidabd.onelibrary.presentation

import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.onelibrary.di.apiModule
import com.wahidabd.onelibrary.di.coroutineModule
import com.wahidabd.onelibrary.di.features.movieModule
import com.wahidabd.onelibrary.di.rxModule
import org.koin.core.module.Module
import timber.log.Timber

class App : BaseApplication() {

    override fun getDefineModule(): List<Module> =
        listOf(
            apiModule,
            rxModule,
            coroutineModule,
            movieModule
        )

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }
}