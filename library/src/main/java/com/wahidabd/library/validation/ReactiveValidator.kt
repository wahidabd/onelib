package com.wahidabd.library.validation

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.library.validation.view.ValidationAutoCompleteTextView


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class ReactiveValidator(validations: MutableList<Validation>): Validator {

    private var validated = 0

    override var mListener: ValidationListener?
        get() = mListener
        set(value) {mListener = value}

    override val validations: MutableList<Validation>
        get() = this.validations

    override fun addValidation(validation: Validation) {
       val view = validation.getView
        if (view is EditText){
           view.onTextChange{
               executeValidation(validation)
           }
       }else if (view is ValidationAutoCompleteTextView){
        }
    }

    override fun setListener(listener: ValidationListener) {
        mListener = listener
    }

    override fun validate(): Boolean {
        TODO("Not yet implemented")
    }

    private fun executeValidation(validation: Validation) {
        val isValidate = validation.validate()
        var tempValidated = 0

        if (isValidate){
            if (isValidate != validation.getPreviousValidationResult()){
                validated++
            }else if (isValidate != validation.getPreviousValidationResult() && validated > 0){
                tempValidated = validated
                validated = tempValidated + -1
            }
        }

        if (validated == validations.size){
            mListener?.onValidationSuccess()
        }else{
            mListener?.onValidationFailed()
        }
    }
}