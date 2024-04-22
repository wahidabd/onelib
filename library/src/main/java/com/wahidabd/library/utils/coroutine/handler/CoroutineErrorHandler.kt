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


fun <T> coroutineErrorHandler(e: Throwable): Resource<T> =
    when(e){
        is SocketTimeoutException -> Resource.fail(e, "Connection Time Out")
        is SSLHandshakeException -> Resource.fail(e, "SSL Certificate not matched")
        is SSLPeerUnverifiedException ->  Resource.fail(e, "SSL Certificate not matched")
        is IOException ->  Resource.fail(e, "Connection IOException")
        is JsonSyntaxException -> Resource.fail(e, "JSON Exception")
        else -> Resource.fail(e, e.message ?: "An unknown error occurred")
    }