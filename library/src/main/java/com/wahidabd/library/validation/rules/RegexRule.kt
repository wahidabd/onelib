package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractInputTypedView
import com.wahidabd.library.validation.util.regexMatchers


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class RegexRule(private val regexRule: String, override val errorMessage: String) : Rule {

    override fun isRulePassed(view: View): Boolean =
        regexMatchers(
            extractInputTypedView(view),
            regexRule
        )

}