package com.wahidabd.library.validation.util

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.presentation.view.InputView
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.validation.RuleNotApplicableException
import java.util.regex.Pattern

internal fun extractCheckedTypedView(view: View): Boolean =
    when(view){
        is CheckBox -> view.isChecked
        is RadioButton -> view.isChecked
        is RadioGroup -> view.checkedRadioButtonId != -1
        else -> {
            TODO()
        }
    }

internal fun extractInputTypedView(view: View): String =
    when (view){
        is EditText -> {
            view.text.toString().trim()
        }
        is TextInputLayout -> {
            view.editText?.text.toString().trim()
        }
        is InputView -> {
            view.getText().trim()
        }
        else -> emptyString()
    }

internal fun regexMatchers(input: String, regexRule: String): Boolean {
    val pattern = Pattern.compile(regexRule)
    val matcher = pattern.matcher(input)
    return matcher.matches()
}