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


/**
 * Extracts and trims the input from the given view, removing leading spaces and newlines.
 *
 * This function handles different types of views such as EditText, TextInputLayout, and custom InputView.
 * It converts the input to a string, removes all spaces and newlines, and trims leading whitespace.
 *
 * @param view The view from which the input is extracted.
 * @return The extracted and trimmed input string.
 * @throws RuleNotApplicableException if the view is not an applicable input view type.
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


/**
 * Extracts the input from the given view without trimming any characters.
 *
 * This function handles different types of views such as EditText, TextInputLayout, and custom InputView.
 * It converts the input to a string without removing any spaces or newlines.
 *
 * @param view The view from which the input is extracted.
 * @return The extracted input string.
 * @throws RuleNotApplicableException if the view is not an applicable input view type.
 */
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