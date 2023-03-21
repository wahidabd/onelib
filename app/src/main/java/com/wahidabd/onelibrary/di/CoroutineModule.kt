package com.wahidabd.onelibrary.di

import com.wahidabd.library.base.coroutine.ErrorParses
import org.koin.dsl.module


/**
 * Created by Wahid on 3/21/2023.
 * Github wahidabd.
 */

val coroutineModule = module {
    single { ErrorParses(get()) }
}