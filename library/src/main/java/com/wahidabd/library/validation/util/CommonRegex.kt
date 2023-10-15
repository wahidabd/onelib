package com.wahidabd.library.validation.util

object CommonRegex {
    const val EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val PASSWORD_ALPHANUMERIC = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\d)[a-zA-Z0-9\\\\d]{8,}$"
    const val PASSWORD_SPECIAL_CHAR = "^[\\s\\d\\w!@#$%^&_*]{6,}$"
    const val NUMBER_ONLY = "^[0-9]{0,}$"
    const val ALPHABET_ONLY = "^[a-zA-Z]{0,}$"
    const val NOT_EMPTY = "(\\n|.)+"
    const val ALPHABET_SPACE_ONLY = "^[a-zA-Z]+[a-zA-Z ]*$"
    const val INDONESIAN_PHONE_NUMBER_NATIONAL_FORMAT = "^(08)[\\d]{8,11}$"
    const val INDONESIAN_PHONE_NUMBER_E164_FORMAT = "^(\\+62)[\\d]{9,12}$"
}