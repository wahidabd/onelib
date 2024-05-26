package com.wahidabd.library.validation

interface ValidationListener {
    fun onValidationFailed()
    fun onValidationSuccess()
}