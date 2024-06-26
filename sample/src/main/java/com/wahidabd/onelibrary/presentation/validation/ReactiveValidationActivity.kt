package com.wahidabd.onelibrary.presentation.validation

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.exts.disable
import com.wahidabd.library.utils.exts.enable
import com.wahidabd.library.validation.ReactiveFormActivity
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.alphabetOnlyRule
import com.wahidabd.library.validation.util.alphabetSpaceOnly
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.minMaxLengthRule
import com.wahidabd.library.validation.util.notEmptyRule
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityReactiveValidationBinding
import com.wahidabd.onelibrary.utils.Constant

class ReactiveValidationActivity : ReactiveFormActivity<ActivityReactiveValidationBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ReactiveValidationActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityReactiveValidationBinding =
        ActivityReactiveValidationBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

    override fun onValidationFailed() {
        binding.btnValidation.disable()
    }

    override fun onValidationSuccess() {
        binding.btnValidation.enable()
    }

    override fun setupValidation() {
        with(binding) {
            addValidation(
                Validation(
                    tilEmail, listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        emailRule(getString(R.string.error_email_invalid))
                    )
                ),
                Validation(
                    tilPassword, listOf(
                        notEmptyRule(getString(R.string.error_field_required))
                    )
                ),
                Validation(
                    tilPhone, listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        minMaxLengthRule(
                            getString(R.string.error_phone_number),
                            Constant.MIN_LENGTH,
                            Constant.MAX_LENGTH
                        )
                    )
                ),
                Validation(
                    tilName, listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        alphabetOnlyRule(getString(R.string.error_alphabet))
                    )
                ),
                Validation(
                    tilLongName, listOf(
                        alphabetSpaceOnly(getString(R.string.error_field_required))
                    )
                ),
                Validation(
                    tilNotEmpty, listOf(
                        notEmptyRule(getString(R.string.error_field_required))
                    )
                )
            )
        }
    }
}