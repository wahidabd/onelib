package com.wahidabd.onelibrary.presentation.validation

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.PassiveFormActivity
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.alphabetOnlyRule
import com.wahidabd.library.validation.util.alphabetSpaceOnly
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.minMaxLengthRule
import com.wahidabd.library.validation.util.notEmptyRule
import com.wahidabd.library.validation.util.passwordRule
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityPassiveValidationBinding
import com.wahidabd.onelibrary.utils.Constant.MAX_LENGTH
import com.wahidabd.onelibrary.utils.Constant.MIN_LENGTH

class PassiveValidationActivity : PassiveFormActivity<ActivityPassiveValidationBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PassiveValidationActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityPassiveValidationBinding =
        ActivityPassiveValidationBinding.inflate(layoutInflater)


    override fun initUI() {}

    override fun initAction() {
        binding.btnValidation.onClick {
            validate()
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    override fun onValidationSuccess() {
        // Do something when validation success
    }

    override fun onValidationFailed() {
        // Do something when validation failed
    }

    override fun setupValidation() {
        with(binding) {
            addValidation(
                Validation(
                    tilEmail,
                    listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        emailRule(getString(R.string.error_email_invalid))
                    )
                ),
                Validation(
                    tilPassword, listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        passwordRule(getString(R.string.error_password))
                    )
                ),
                Validation(
                    tilPhone, listOf(
                        notEmptyRule(getString(R.string.error_field_required)),
                        minMaxLengthRule(
                            getString(R.string.error_phone_number),
                            MIN_LENGTH,
                            MAX_LENGTH
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
                        notEmptyRule(getString(R.string.error_empty))
                    )
                )
            )
        }
    }
}