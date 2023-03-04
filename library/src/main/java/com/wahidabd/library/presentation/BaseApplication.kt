package com.wahidabd.library.presentation

import android.app.Application

abstract class BaseApplication : Application() {

    abstract fun getDefineModule()
    abstract fun initApp()

    override fun onCreate() {
        super.onCreate()
    }

}