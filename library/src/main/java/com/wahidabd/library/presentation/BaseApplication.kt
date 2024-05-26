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

/**
 * The BaseApplication class serves as an abstract base class for the Application class.
 * It sets up the context provider and initializes the Koin dependency injection framework.
 */
abstract class BaseApplication : Application() {

    /**
     * Gets the list of Koin modules to be used for dependency injection.
     *
     * @return List of Koin Module objects.
     */
    abstract fun getDefineModule(): List<Module>

    /**
     * Initializes application-specific components. This method should be implemented
     * by subclasses to perform any necessary initialization.
     */
    abstract fun initApp()

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * have been created. Initializes the context provider and starts Koin with the defined modules.
     */
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