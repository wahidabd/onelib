package com.wahidabd.library.presentation.view

interface ErrorableView {
    fun hideError(): Unit

    fun isErrorShowing(): Boolean

    fun showError(errorMessage: String)
}