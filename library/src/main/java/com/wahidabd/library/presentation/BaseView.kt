package com.wahidabd.library.presentation

import androidx.appcompat.widget.Toolbar

interface BaseView {
    fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean)
    fun setupToolbar(toolbar: Toolbar?, isChild: Boolean)
    fun setupToolbar(title: String, isChild: Boolean)
    fun showLoading()
    fun hideLoading()
    fun finishActivity()
}