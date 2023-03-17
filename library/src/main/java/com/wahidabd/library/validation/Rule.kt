package com.wahidabd.library.validation

import android.view.View

interface Rule {
    abstract val errorMessage: String
    abstract fun isRulePassed(view: View): Boolean
}