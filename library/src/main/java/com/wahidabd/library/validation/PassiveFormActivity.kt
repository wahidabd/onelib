package com.wahidabd.library.validation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by Wahid on 9/19/2023.
 * Github github.com/wahidabd.
 */

/**
 * Abstract class PassiveFormActivity
 *
 * A base activity class that provides form validation functionality using the PassiveValidator.
 * This class sets up the validator, manages validation listeners, and provides methods to add validations
 * and trigger validation checks.
 *
 * @param VB The type of ViewBinding associated with this activity.
 *
 * Example usage:
 * ```kotlin
 * class MyFormActivity : PassiveFormActivity<ActivityMyFormBinding>() {
 *     override fun setupValidation() {
 *         addValidation(
 *              Validation(
 *                  tilEmail,
 *                  listOf(
 *                      notEmptyRule("message"),
 *                      emailRule("message")
 *                  )
 *              ),
 *             Validation(
 *                  tilPassword, listOf(
 *                      notEmptyRule("message"),
 *                      passwordRule("message")
 *                  )
 *             )
 *         )
 *     }
 *
 *     override fun onValidationSuccess() {
 *         // Handle validation success
 *     }
 *
 *     override fun onValidationFailed() {
 *         // Handle validation failure
 *     }
 * }
 * ```
 */
abstract class PassiveFormActivity<VB : ViewBinding> : BaseActivity<VB>(), ValidationListener {

    private val validator = PassiveValidator(arrayListOf())

    /**
     * Initializes the intent and sets up the validation listener and validation rules.
     * This method is called during the activity initialization.
     */
    override fun initIntent() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    /**
     * Adds one or more validations to the validator.
     *
     * @param validations The validations to be added.
     */
    fun addValidation(vararg validations: Validation) {
        validations.forEach { validation ->
            this.validator.addValidation(validation)
        }
    }

    /**
     * Validates all added validations.
     *
     * @return `true` if all validations pass, `false` otherwise.
     */
    fun validate(): Boolean = validator.validate()

    /**
     * Abstract method to be implemented by subclasses to set up validation rules.
     * This method should add all necessary validations for the form.
     */
    abstract fun setupValidation()
}