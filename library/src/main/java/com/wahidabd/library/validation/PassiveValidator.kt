package com.wahidabd.library.validation


/**
 * Created by Wahid on 9/19/2023.
 * Github github.com/wahidabd.
 */

/**
 * The PassiveValidator class implements the Validator interface and provides
 * mechanisms to manage and validate a list of Validation objects.
 *
 * @param validations A mutable list of Validation objects to be managed by this validator.
 */
class PassiveValidator(override val validations: MutableList<Validation>) : Validator {

    /**
     * The listener that will receive validation events.
     */
    override var mListener: ValidationListener? = null

    /**
     * Sets the listener to receive validation events.
     *
     * @param listener The listener to be notified of validation events.
     */
    override fun setListener(listener: ValidationListener) {
        mListener = listener
    }

    /**
     * Adds a validation to the list of validations.
     *
     * @param validation The Validation object to be added.
     */
    override fun addValidation(validation: Validation) {
        validations.add(validation)
    }

    /**
     * Validates all the Validation objects in the list.
     *
     * @return Boolean indicating whether all validations were successful or not.
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

}