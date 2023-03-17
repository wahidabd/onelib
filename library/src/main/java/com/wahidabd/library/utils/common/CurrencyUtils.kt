package com.wahidabd.library.utils.common

import java.text.NumberFormat
import java.util.*

const val COUNTRY_ID = "id"
const val LANGUAGE_ID = "ID"

private fun getNormalizedValue(value: String): String =
    value.substring(2, value.length)

fun Locale.getCurrencySymbol(): String =
    Currency.getInstance(this).getSymbol(this)

fun Double.toCurrency(locale: Locale, fractionDigit: Int): String {
    val format = NumberFormat.getCurrencyInstance(locale)
    format.maximumFractionDigits = fractionDigit
    return format.format(this)
}

fun Double.toCurrencyValue(locale: Locale, fractionDigit: Int): String {
    val format = NumberFormat.getCurrencyInstance(locale)
    format.maximumFractionDigits = fractionDigit
    val value = format.format(this)
    return getNormalizedValue(value)
}