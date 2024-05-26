package com.wahidabd.library.validation

interface Validator  {

    var mListener: ValidationListener?
    val validations: MutableList<Validation>

    fun addValidation(validation: Validation)
    fun setListener(listener: ValidationListener)
    fun validate(): Boolean

}