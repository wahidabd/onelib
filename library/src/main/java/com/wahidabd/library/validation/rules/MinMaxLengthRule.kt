package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractInputTypedView
import com.wahidabd.library.validation.util.regexMatchers


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class MinMaxLengthRule(
    private val minLength: String,
    private val maxLength: String,
    override val errorMessage: String
) : Rule {

    override fun isRulePassed(view: View): Boolean {
        val regexRule = "^[\\s\\S]{$minLength,$maxLength}$";
        val input = extractInputTypedView(view)
        return regexMatchers(input, regexRule)
    }

}