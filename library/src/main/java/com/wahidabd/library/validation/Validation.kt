package com.wahidabd.library.validation

import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.presentation.view.ErrorableView

class Validation(private val view: View, private val rules: List<Rule>) {

    private var isValid = false
    private var ruleNotPassedIndex = -1
    private var validationResultHistory = Pair(false, false)
    var getView = view

    fun getPreviousValidationResult(): Boolean =
        validationResultHistory.second

    private fun updateValidationHistory(valid: Boolean) {
        val old = validationResultHistory.copy()
        validationResultHistory = Pair(valid, old.first)
    }

    fun validate(): Boolean {
        var index = 0
        if (index < rules.size) {
            do {
                val rule = rules[index]
                if (!rule.isRulePassed(view)) {
                    isValid = false
                    ruleNotPassedIndex = index
                    val errorMessage = rule.errorMessage

                    when (view) {
                        is EditText -> view.error = errorMessage
                        is TextInputLayout -> view.error = errorMessage
                        is ErrorableView -> view.showError(errorMessage)
                    }
                    break
                }

                isValid = true
                when (view) {
                    is EditText -> {
                        if (index == rules.size - 1 && view.error != null) view.error = null
                    }

                    is TextInputLayout -> {
                        if (index == rules.size - 1 && view.error != null) view.error = null
                    }

                    is ErrorableView -> {
                        if (index == rules.size - 1 && view.isErrorShowing()) view.hideError()
                    }
                }
                index++
            } while (index < rules.size)
        }

        updateValidationHistory(isValid)
        return isValid
    }

}