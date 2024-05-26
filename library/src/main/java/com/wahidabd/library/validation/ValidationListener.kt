package com.wahidabd.library.validation

interface ValidationListener {
    abstract fun onValidationFailed()
    abstract fun onValidationSuccess()
}