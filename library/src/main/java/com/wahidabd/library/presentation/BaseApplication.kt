package com.wahidabd.library.presentation

import android.app.Application
import com.wahidabd.library.utils.common.ContextProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    abstract fun getDefineModule(): List<Module>
    abstract fun initApp()

    override fun onCreate() {
        super.onCreate()

        ContextProvider.INSTANCE.initialize(applicationContext)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            androidFileProperties()
            fragmentFactory()
            modules(getDefineModule())
        }

        initApp()
    }

}