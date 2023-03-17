package com.wahidabd.library.utils.common

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat
import com.google.i18n.phonenumbers.Phonenumber

private fun formatNumber(
    phoneNumber: String,
    format: PhoneNumberFormat,
    countryCode: String
): String? {
    val phoneUtil = PhoneNumberUtil.getInstance()

    return try {
        val phone: Phonenumber.PhoneNumber = phoneUtil.parse(phoneNumber, countryCode)
        phoneUtil.format(phone, format)
    }catch (e: NumberParseException){
        e.printStackTrace()
        null
    }
}

fun formatNumberToE164(
    phoneNumber: String,
    countryCode: String
): String = formatNumber(phoneNumber, PhoneNumberFormat.E164, countryCode).toString()

fun formatNumberToInternational(
    phoneNumber: String,
    countryCode: String
): String = formatNumber(phoneNumber, PhoneNumberFormat.INTERNATIONAL, countryCode).toString()

fun formatNumberToNational(
    phoneNumber: String,
    countryCode: String
): String = formatNumber(phoneNumber, PhoneNumberFormat.NATIONAL, countryCode).toString()

fun formatNumberToRFC3966(
    phoneNumber: String,
    countryCode: String
): String = formatNumber(phoneNumber, PhoneNumberFormat.RFC3966, countryCode).toString()

fun isValidNumber(
    phoneNumber: String,
    countryCode: String
): Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    return try {
        val phone = phoneUtil.parse(phoneNumber, countryCode)
        phoneUtil.isValidNumber(phone)
    }catch (e: NumberParseException){
        e.printStackTrace()
        false
    }
}