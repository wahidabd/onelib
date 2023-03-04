package com.wahidabd.library.presentation

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    abstract fun getDefineModule(): List<Module>
    abstract fun initApp()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            getDefineModule()
        }

        initApp()
    }

}