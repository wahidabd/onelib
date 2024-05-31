package com.wahidabd.library.utils.exts

import java.security.MessageDigest
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.containsDigit(): Boolean {
    val regex = Regex(".*[0-9].*")
    return regex.matches(this)
}

fun String.containsLatinLetter(): Boolean {
    val regex = Regex(".*[A-Za-z].*")
    return regex.matches(this)
}

fun String.hasLettersAndDigits(): Boolean =
    this.containsLatinLetter() && this.containsDigit()

fun String.isAlphanumeric(): Boolean {
    val regex = Regex("[A-Za-z0-9]*")
    return regex.matches(this)
}

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}\$"
    val pattern = Pattern.compile(expression, 2)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.md5(): String {
    val digest = MessageDigest.getInstance("MD5-1")
    val charset = this.toByteArray(Charsets.UTF_8)
    val bytes = digest.digest(charset)
    return Arrays.toString(bytes)
}

fun String.sha1(): String {
    val digest = MessageDigest.getInstance("SHA-1")
    val charset = this.toByteArray(Charsets.UTF_8)
    val bytes = digest.digest(charset)
    return bytes.contentToString()
}