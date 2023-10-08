package com.wahidabd.library.validation.util

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.validation.Rule
import com.wahidabd.library.validation.rules.CheckedRule
import com.wahidabd.library.validation.rules.CustomRule
import com.wahidabd.library.validation.rules.MinMaxLengthRule
import com.wahidabd.library.validation.rules.NotEmptyRule
import com.wahidabd.library.validation.rules.RegexRule

fun alphaNumericPasswordRule(errorMessage: String): RegexRule =
    RegexRule("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\d)[a-zA-Z0-9\\\\d]{8,}$", errorMessage)

fun alphabetOnlyRule(errorMessage: String): RegexRule =
    RegexRule("^[a-zA-Z]{0,}\$", errorMessage)

fun alphabetSpaceOnly(errorMessage: String): RegexRule =
    RegexRule("^[a-zA-Z]+[a-zA-Z ]*$", errorMessage)

fun checkedRule(errorMessage: String): CheckedRule =
    CheckedRule(errorMessage)

fun customRule(errorMessage: String, rule: () -> Boolean): CustomRule =
    CustomRule(rule, errorMessage)

fun emailRule(errorMessage: String): RegexRule =
    RegexRule("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", errorMessage)

fun indonesianPhoneNumberE164FormatOnly(errorMessage: String): RegexRule =
    RegexRule("^(\\+62)[\\d]{9,12}$", errorMessage)

fun indonesianPhoneNumberNationalFormatOnly(errorMessage: String): RegexRule =
    RegexRule("^(08)[\\d]{8,11}$", errorMessage)

fun maxLengthRule(errorMessage: String, maxLength: Int): MinMaxLengthRule =
    minMaxLengthRule(errorMessage, null, maxLength)

fun minLengthRule(errorMessage: String, minLength: Int?): MinMaxLengthRule =
    minMaxLengthRule(errorMessage, minLength, null)

fun minMaxLengthRule(errorMessage: String, minLength: Int? = 0, maxLength: Int?): MinMaxLengthRule{
    var result = ""
    var min = ""
    var max = ""

    if (minLength != null){
        val convertToString = minLength.toString()
        result = convertToString.ifBlank { "0" }
        min = result
    }

    if (maxLength != null){
        val convertToString = maxLength.toString()
        result = convertToString.ifBlank { "0" }
        max = result
    }

    return MinMaxLengthRule(min, max, errorMessage)
}

fun notEmptyRule(errorMessage: String): NotEmptyRule =
    NotEmptyRule(errorMessage)

fun numberOnlyRule(errorMessage: String): RegexRule =
    RegexRule("^[0-9]{0,}$", errorMessage)

fun passwordRule(errorMessage: String): RegexRule =
    RegexRule("^[\\s\\d\\w!@#$%^&_*]{6,}$", errorMessage)

fun regexRule(errorMessage: String, rule: String): RegexRule =
    RegexRule(rule, errorMessage)

//fun spinnerRule(errorMessage: String, defaultItem: Int): SpinR