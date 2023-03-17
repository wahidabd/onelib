package com.wahidabd.library.validation.rules

import android.view.View
import com.wahidabd.library.validation.Rule


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class PhoneNumberRule(override val errorMessage: String) : Rule {
    override fun isRulePassed(view: View): Boolean = false
}