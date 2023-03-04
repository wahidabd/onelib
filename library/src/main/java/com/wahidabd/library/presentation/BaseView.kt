package com.wahidabd.library.presentation

import androidx.appcompat.widget.Toolbar

interface BaseView {
    fun setupToolbar(var1: Toolbar?, var2: String, var3: Boolean)
    fun setupToolbar(var1: Toolbar?, var2: Boolean)
    fun setupToolbar(var1: String, var2: Boolean)
    fun showLoading()
    fun hideLoading()
    fun finishActivity()
}