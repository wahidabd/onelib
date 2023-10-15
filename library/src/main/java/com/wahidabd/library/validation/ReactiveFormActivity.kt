package com.wahidabd.library.validation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */

abstract class ReactiveFormActivity<VB: ViewBinding> : BaseActivity<VB>(), ValidationListener{

    private val validator = ReactiveValidator(arrayListOf())

    override fun initIntent() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    protected fun addValidation(validation: Validation) {
        validator.addValidation(validation)
    }

    protected fun validate(): Boolean = validator.validate()
    abstract fun setupValidation()

}