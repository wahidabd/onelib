package com.wahidabd.library.validation.rules

import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.presentation.view.InputView
import com.wahidabd.library.validation.RuleNotApplicableException


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

internal fun extractInputTypedViewTrimStart(view: View): String =
    when(view){
        is EditText -> {
            view.text.toString().replace(" ", "").replace("\n", "").trimStart()
        }
        is TextInputLayout -> {
            view.editText?.text.toString().replace(" ", "").replace("\n", "").trimStart()
        }
        else -> {
            if (view !is InputView) throw RuleNotApplicableException("this view is not applicable")
            view.getText().replace(" ", "").replace("\n", "").trimStart()
        }
    }

internal fun extractInputTypedViewTrim(view: View): String =
    when(view){
        is EditText -> {
            view.text.toString()
        }
        is TextInputLayout -> {
            view.editText?.text.toString()
        }
        else -> {
            if (view !is InputView) throw RuleNotApplicableException("this view is not applicable")
            view.getText()
        }
    }