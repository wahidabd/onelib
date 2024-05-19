package com.wahidabd.library.validation


/**
 * Created by Wahid on 9/19/2023.
 * Github github.com/wahidabd.
 */


class PassiveValidator(override val validations: MutableList<Validation>) : Validator {

    override var mListener: ValidationListener? = null
    override fun setListener(listener: ValidationListener) {
        mListener = listener
    }

    override fun addValidation(validation: Validation) {
        validations.add(validation)
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

}