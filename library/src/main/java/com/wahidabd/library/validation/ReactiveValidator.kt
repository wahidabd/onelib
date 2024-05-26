package com.wahidabd.library.validation

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.library.validation.view.ValidationAutoCompleteTextView


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


/**
 * Class ReactiveValidator
 *
 * A reactive validator that dynamically validates inputs as they change.
 * This validator supports various input views and triggers validation based on user interactions.
 *
 * @property validations A mutable list of validations to be executed.
 *
 * @constructor
 * Creates a ReactiveValidator with a specified list of validations.
 *
 * @param validations The initial list of validations to be managed by this validator.
 */
class ReactiveValidator(override val validations: MutableList<Validation>) : Validator {

    private var validated = 0

    override var mListener: ValidationListener? = null

    /**
     * Adds a new validation to the validator and sets up the necessary listeners
     * to reactively validate the input.
     *
     * @param validation The validation to be added.
     */
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

    /**
     * Sets a listener to receive validation events.
     *
     * @param listener The listener to be notified of validation events.
     */
    override fun setListener(listener: ValidationListener) {
        mListener = listener
    }

    /**
     * Validates all managed validations.
     *
     * @return `true` if all validations pass, `false` otherwise.
     */
    override fun validate(): Boolean {
        var isValid = true

        validations.forEach { validation ->
            if (!validation.validate()) isValid = false
        }

        if (isValid) mListener?.onValidationSuccess()
        else mListener?.onValidationFailed()
        return isValid
    }

    /**
     * Executes a single validation and updates the validation state.
     *
     * @param validation The validation to be executed.
     */
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