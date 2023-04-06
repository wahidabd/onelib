package com.wahidabd.library.validation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */

abstract class ReactiveFormActivity<VB: ViewBinding> : BaseActivity<VB>(), ValidationListener{


    protected fun addValidation(validation: Validation) {

    }


    protected abstract fun setupValidation()
    protected fun validate(): Boolean {
        return false
    }

}