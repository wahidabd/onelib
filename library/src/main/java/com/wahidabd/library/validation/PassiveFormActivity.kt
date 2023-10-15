package com.wahidabd.library.validation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by Wahid on 9/19/2023.
 * Github github.com/wahidabd.
 */


abstract class PassiveFormActivity<VB: ViewBinding> : BaseActivity<VB>(), ValidationListener {

    private val validator = PassiveValidator(arrayListOf())

    override fun initIntent() {
        this.validator.setListener(this)
        this.setupValidation()
    }

    fun addValidation(validation: Validation){
        this.validator.addValidation(validation)
    }

    fun validate(): Boolean = validator.validate()

    abstract fun setupValidation()
}