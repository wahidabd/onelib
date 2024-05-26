package com.wahidabd.library.validation

import android.view.View

/**
 * The Rule interface defines a contract for validation rules that can be applied
 * to a View. Each rule has an error message and a method to check if the rule
 * is passed.
 */
interface Rule {
    /**
     * The error message to be displayed if the rule is not passed.
     */
    abstract val errorMessage: String

    /**
     * Checks if the rule is passed for the given view.
     *
     * @param view The View to which the rule is applied.
     * @return Boolean indicating whether the rule is passed.
     */
    abstract fun isRulePassed(view: View): Boolean
}