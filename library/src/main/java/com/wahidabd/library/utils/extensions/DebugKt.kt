package com.wahidabd.library.utils.extensions

import timber.log.Timber


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */

fun debug(t: Throwable? = null, message: () -> String) {
    if (Timber.treeCount > 0){
        Timber.d(t, message.invoke())
    }
}


fun Timber.Tree.debug(t: Throwable?, message: () -> String){
    if (Timber.treeCount > 0){
        this.d(t, message.invoke())
    }
}