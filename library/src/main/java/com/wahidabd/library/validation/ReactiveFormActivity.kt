package com.wahidabd.library.validation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */

/**
 * Abstract class ReactiveFormActivity is a generic base class for activities that use
 * reactive form validation. It extends BaseActivity and implements ValidationListener.
 *
 * @param VB The type of ViewBinding associated with this activity.
 */
abstract class ReactiveFormActivity<VB: ViewBinding> : BaseActivity<VB>(), ValidationListener{

    private val validator = ReactiveValidator(arrayListOf())

    /**
     * Initializes the intent and sets up validation by setting this activity as the
     * listener for the validator and calling the abstract setupValidation method.
     */
    override fun initIntent() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    /**
     * Adds one or more validations to the validator.
     *
     * @param validations Vararg parameter of Validation objects to be added.
     */
    protected fun addValidation(vararg validations: Validation) {
        validations.forEach {
            this.validator.addValidation(it)
        }
    }

    /**
     * Validates the current state of the form using the validator.
     *
     * @return Boolean indicating whether the validation was successful or not.
     */
    protected fun validate(): Boolean = validator.validate()

    /**
     * Abstract method to be implemented by subclasses to define their validation logic.
     */
    abstract fun setupValidation()
}