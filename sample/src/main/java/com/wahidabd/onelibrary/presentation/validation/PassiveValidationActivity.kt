package com.wahidabd.onelibrary.presentation.validation

import android.content.Context
import android.content.Intent
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.validation.PassiveFormActivity
import com.wahidabd.library.validation.PassiveValidator
import com.wahidabd.library.validation.Validation
import com.wahidabd.library.validation.util.alphabetOnlyRule
import com.wahidabd.library.validation.util.alphabetSpaceOnly
import com.wahidabd.library.validation.util.emailRule
import com.wahidabd.library.validation.util.indonesianPhoneNumberE164FormatOnly
import com.wahidabd.library.validation.util.indonesianPhoneNumberNationalFormatOnly
import com.wahidabd.library.validation.util.minMaxLengthRule
import com.wahidabd.library.validation.util.notEmptyRule
import com.wahidabd.library.validation.util.passwordRule
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityPassiveValidationBinding
import com.wahidabd.onelibrary.utils.Constant
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
        showToast("Success")
    }

    override fun onValidationFailed() {
        showToast("Failed")
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
                )
            )

            addValidation(
                Validation(
                    tilPassword, listOf(
                        notEmptyRule("Wajib diisi"),
                        passwordRule("Password harus angka dan huruf")
                    )
                )
            )

            addValidation(
                Validation(
                    tilPhone, listOf(
                        notEmptyRule("Wajib diisi"),
                        minMaxLengthRule(
                            "masukan nomor telpon yang benar",
                            MIN_LENGTH,
                            MAX_LENGTH
                        )
                    )
                )
            )

            addValidation(
                Validation(
                    tilName, listOf(
                        notEmptyRule("Wajib diisi"),
                        alphabetOnlyRule("alphabet only")
                    )
                )
            )

            addValidation(
                Validation(
                    tilLongName, listOf(
                        alphabetSpaceOnly("alphabet space only")
                    )
                )
            )

            addValidation(
                Validation(
                    tilNotEmpty, listOf(
                        notEmptyRule("Cannot be empty")
                    )
                )
            )
        }
    }

}