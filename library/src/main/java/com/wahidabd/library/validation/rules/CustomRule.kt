package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class CustomRule(private val customRule: () -> Boolean, override val errorMessage: String) : Rule {

    override fun isRulePassed(view: View): Boolean =
        customRule.invoke()

}