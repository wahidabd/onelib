package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */


/**
 * A validation rule that allows for custom logic to be defined for checking if an input is valid.
 * This class uses a lambda function to determine the validity of the input.
 *
 * @property customRule A lambda function that defines the custom validation logic.
 * @property errorMessage The error message to be displayed if the rule is not passed.
 *
 * @constructor
 * Creates a CustomRule with a specific validation logic and error message.
 *
 * @param customRule A lambda function that returns `true` if the input is valid according to the custom logic, `false` otherwise.
 * @param errorMessage The error message to be displayed if the rule is not passed.
 */
class CustomRule(private val customRule: () -> Boolean, override val errorMessage: String) : Rule {

    /**
     * Checks if the input in the given view passes the custom rule.
     *
     * This method invokes the custom rule lambda function to determine if the input
     * meets the specified validation criteria.
     *
     * @param view The view from which the input is extracted.
     * @return `true` if the input meets the custom validation logic, `false` otherwise.
     */
    override fun isRulePassed(view: View): Boolean =
        customRule.invoke()

}