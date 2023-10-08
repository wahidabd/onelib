package com.wahidabd.library.validation

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.library.validation.view.ValidationAutoCompleteTextView


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class ReactiveValidator(override val validations: MutableList<Validation>) : Validator {

    private var validated = 0

    override var mListener: ValidationListener? = null


    override fun addValidation(validation: Validation) {
        when (val view = validation.getView) {
            is EditText -> {
                view.onTextChange {
                    executeValidation(validation)
                }
            }

            is ValidationAutoCompleteTextView -> {
                view.autoCompleteTextView.onTextChange {
                    executeValidation(validation)
                }
            }

            is TextInputLayout -> {
                view.onTextChange {
                    executeValidation(validation)
                }
            }

            is RadioGroup -> {
                view.setOnCheckedChangeListener { _, _ ->
                    executeValidation(validation)
                }
            }

            is CheckBox -> {
                view.setOnCheckedChangeListener { _, checked ->
                    if (checked) executeValidation(validation)
                }
            }
        }

        validations.add(validation)
    }

    override fun setListener(listener: ValidationListener) {
        mListener = listener
    }

    override fun validate(): Boolean {
        var isValid = true

        validations.forEach { validation ->
            if (!validation.validate()) isValid = false
        }

        if (isValid) mListener?.onValidationSuccess()
        else mListener?.onValidationFailed()
        return isValid
    }

    private fun executeValidation(validation: Validation) {
        val isValidate = validation.validate()
        var tempValidated = 0

        if (isValidate != validation.getPreviousValidationResult()) {
            tempValidated = validated++
        } else if (isValidate != validation.getPreviousValidationResult() && validated > 0) {
            tempValidated = validated
            validated = tempValidated + -1
        }

        if (validated == validations.size) mListener?.onValidationSuccess()
        else mListener?.onValidationFailed()
    }
}