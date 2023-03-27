package com.wahidabd.library.utils.rx.apihandlers

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.exception.ApiException
import okio.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */

fun <T> genericErrorHandler(e: Throwable, resource: MutableLiveData<Resource<T>>) {
    when (e){
        is ApiException -> resource.value = Resource.fail(e, e.apiError.message)
        is SocketTimeoutException -> resource.value = Resource.fail(e, "Connection Time Out")
        is SSLHandshakeException -> resource.value = Resource.fail(e, "SSL Certificate not matched")
        is SSLPeerUnverifiedException -> resource.value = Resource.fail(e, "SSL Certificate not matched")
        is IOException -> resource.value = Resource.fail(e, "Connection IOException")
        is JsonSyntaxException -> resource.value = Resource.fail(e, "JSON Exception")
        else -> resource.value = Resource.fail(e, "An unknown error occurred")
    }
}

fun <T> coroutineErrorHandler(e: Throwable): Resource<T> =
    when(e){
        is ApiException -> Resource.fail(e, e.apiError.message)
        is SocketTimeoutException -> Resource.fail(e, "Connection Time Out")
        is SSLHandshakeException -> Resource.fail(e, "SSL Certificate not matched")
        is SSLPeerUnverifiedException ->  Resource.fail(e, "SSL Certificate not matched")
        is IOException ->  Resource.fail(e, "Connection IOException")
        is JsonSyntaxException -> Resource.fail(e, "JSON Exception")
        else -> Resource.fail(e, "An unknown error occurred")
    }