package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.util.extractCheckedTypedView


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class CheckedRule(override val errorMessage: String) : Rule {

    override fun isRulePassed(view: View): Boolean =
        extractCheckedTypedView(view)

}