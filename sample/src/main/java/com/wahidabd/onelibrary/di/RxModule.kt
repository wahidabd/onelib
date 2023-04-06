package com.wahidabd.onelibrary.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module

val rxModule = module {
    factory { CompositeDisposable() }
}