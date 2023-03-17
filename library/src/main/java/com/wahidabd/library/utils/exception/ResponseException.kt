package com.wahidabd.library.utils.exception

import retrofit2.Response


/**
 * Created by Wahid on 3/13/2023.
 * Github wahidabd.
 */
open class ResponseException (response: Response<*>) : RuntimeException() {

    open var response = response

}