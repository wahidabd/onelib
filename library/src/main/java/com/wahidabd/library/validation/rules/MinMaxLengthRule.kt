package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractInputTypedView
import com.wahidabd.library.validation.util.regexMatchers


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */


/**
 * Class MinMaxLengthRule
 *
 * A validation rule that checks if the input length is within a specified minimum and maximum range.
 * This rule ensures that the input string length falls between the defined minLength and maxLength.
 *
 * @property minLength The minimum length of the input string.
 * @property maxLength The maximum length of the input string.
 * @property errorMessage The error message to be displayed if the rule is not passed.
 *
 * @constructor
 * Creates a MinMaxLengthRule with specified minimum and maximum lengths and an error message.
 *
 * @param minLength The minimum allowable length for the input.
 * @param maxLength The maximum allowable length for the input.
 * @param errorMessage The error message to be displayed if the rule is not passed.
 */
class MinMaxLengthRule(
    private val minLength: String,
    private val maxLength: String,
    override val errorMessage: String
) : Rule {

    /**
     * Checks if the input in the given view passes the length rule.
     *
     * This method constructs a regex pattern based on the specified minimum and maximum lengths
     * and verifies if the input length falls within the defined range.
     *
     * @param view The view from which the input is extracted.
     * @return `true` if the input length is within the specified range, `false` otherwise.
     */
    override fun isRulePassed(view: View): Boolean {
        val regexRule = "^[\\s\\S]{$minLength,$maxLength}$"
        val input = extractInputTypedView(view)
        return regexMatchers(input, regexRule)
    }

}