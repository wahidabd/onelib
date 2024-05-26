package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractInputTypedView
import com.wahidabd.library.validation.util.regexMatchers

/**
 * Class AlphabetOnlyRule
 *
 * A validation rule that checks if the input contains only alphabetic characters.
 * This rule ensures that the input string consists of uppercase and lowercase English letters only.
 *
 * @property errorMessage The error message to be displayed if the rule is not passed.
 *
 * @constructor
 * Creates an AlphabetOnlyRule with a specific error message.
 *
 * @param errorMessage The error message to be displayed if the rule is not passed.
 */
class AlphabetOnlyRule(override val errorMessage: String): Rule {
    override fun isRulePassed(view: View): Boolean {
        val input = extractInputTypedView(view)
        return regexMatchers(input, "^[a-zA-Z]{0,}$")
    }
}