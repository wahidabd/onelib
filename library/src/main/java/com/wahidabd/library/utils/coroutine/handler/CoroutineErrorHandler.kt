package com.wahidabd.library.utils.coroutine.handler

import com.google.gson.JsonSyntaxException
import com.wahidabd.library.data.Resource
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException


/**
 * Created by wahid on 4/22/2024.
 * Github github.com/wahidabd.
 */


/**
 * A utility function to handle exceptions in coroutines and map them to a `Resource` object.
 *
 * @param T The type of data that the `Resource` would contain if the operation were successful.
 * @param e The exception that was thrown during the coroutine execution.
 * @return A `Resource<T>` object representing the failure state with an appropriate error message.
 */
fun <T> coroutineErrorHandler(e: Throwable): Resource<T> =
    when(e){
        is SocketTimeoutException -> Resource.fail(e.message ?: "Connection Time Out")
        is SSLHandshakeException -> Resource.fail(e.message ?: "SSL Certificate not matched")
        is SSLPeerUnverifiedException ->  Resource.fail(e.message ?: "SSL Certificate not matched")
        is IOException ->  Resource.fail(e.message ?: "Connection IOException")
        is JsonSyntaxException -> Resource.fail(e.message ?: "JSON Exception")
        else -> Resource.fail(e.message ?: "An unknown error occurred")
    }