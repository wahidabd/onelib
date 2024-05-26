package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.regexMatchers


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

/**
 * Class NotEmptyRule
 *
 * A validation rule that checks if the input is not empty.
 * This rule ensures that the input string is not empty or whitespace only.
 *
 * @property errorMessage The error message to be displayed if the rule is not passed.
 *
 * @constructor
 * Creates a NotEmptyRule with a specific error message.
 *
 * @param errorMessage The error message to be displayed if the rule is not passed.
 */
class NotEmptyRule(override val errorMessage: String) : Rule {

    /**
     * Checks if the input in the given view is not empty.
     *
     * This method extracts the input from the provided view, trims leading whitespace,
     * and verifies if the input contains any non-whitespace characters.
     *
     * @param view The view from which the input is extracted.
     * @return `true` if the input is not empty, `false` otherwise.
     */
    override fun isRulePassed(view: View): Boolean {
        val input = extractInputTypedViewTrimStart(view)
        return regexMatchers(input, "(\\n|.)+")
    }
}