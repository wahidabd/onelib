package com.wahidabd.library.utils.exts

import timber.log.Timber


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */

/**
 * Logs a debug message using Timber if there are any trees planted.
 *
 * @param t An optional throwable to log. Default is null.
 * @param message A lambda function that returns the message to log.
 *
 * Usage:
 * ```
 * debug { "This is a debug message" }
 * debug(Throwable("An error occurred")) { "This is a debug message with an exception" }
 * ```
 */
fun debug(t: Throwable? = null, message: () -> String) {
    if (Timber.treeCount > 0){
        Timber.d(t, message.invoke())
    }
}


/**
 * Logs a debug message using a specific Timber tree if there are any trees planted.
 *
 * @param t An optional throwable to log.
 * @param message A lambda function that returns the message to log.
 *
 * Usage:
 * ```
 * Timber.tag("MyTag").debug(null) { "This is a debug message" }
 * Timber.tag("MyTag").debug(Throwable("An error occurred")) { "This is a debug message with an exception" }
 * ```
 */
fun Timber.Tree.debug(t: Throwable?, message: () -> String){
    if (Timber.treeCount > 0){
        this.d(t, message.invoke())
    }
}