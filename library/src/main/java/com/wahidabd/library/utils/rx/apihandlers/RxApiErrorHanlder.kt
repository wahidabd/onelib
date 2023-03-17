package com.wahidabd.library.utils.rx.apihandlers

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import com.wahidabd.library.data.Result
import com.wahidabd.library.utils.exception.ApiException
import okio.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

fun <T> genericErrorHandler(e: Throwable, result: MutableLiveData<Result<T>>) {
    when (e){
        is ApiException -> result.value = Result.fail(e, e.apiError.message)
        is SocketTimeoutException -> result.value = Result.fail(e, "Connection Time Out")
        is SSLHandshakeException -> result.value = Result.fail(e, "SSL Certificate not matched")
        is SSLPeerUnverifiedException -> result.value = Result.fail(e, "SSL Certificate not matched")
        is IOException -> result.value = Result.fail(e, "Connection IOException")
        is JsonSyntaxException -> result.value = Result.fail(e, "JSON Exception")
        else -> result.value = Result.fail(e, "An unknown error occurred")
    }
}