package com.wahidabd.onelibrary.di

import com.wahidabd.onelibrary.data.AppDatabase
import org.koin.dsl.module


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


val dbModule = module {
    single { AppDatabase.getDatabase(get()) }
}