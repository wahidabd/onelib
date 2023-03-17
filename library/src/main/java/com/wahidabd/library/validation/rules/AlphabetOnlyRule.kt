package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractInputTypedView
import com.wahidabd.library.validation.util.regexMatchers

class AlphabetOnlyRule(override val errorMessage: String): Rule {

    override fun isRulePassed(view: View): Boolean {
        val input = extractInputTypedView(view)
        return regexMatchers(input, "^[a-zA-Z]{0,}$")
    }
}