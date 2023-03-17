package com.wahidabd.library.validation

interface Validator  {

    abstract var mListener: ValidationListener?
    abstract val validations: MutableList<Validation>

    abstract fun addValidation(validation: Validation)
    abstract fun setListener(listener: ValidationListener)
    abstract fun validate(): Boolean

}