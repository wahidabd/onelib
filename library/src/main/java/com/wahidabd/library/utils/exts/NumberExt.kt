package com.wahidabd.library.utils.exts

fun Int?.orZero(maxLength: Int): String = (this ?: 0).toString()

fun Number?.isNotZero(): Boolean = this != 0

fun Double?.orZero(): Double = this ?: 0.0

fun Float?.orZero(): Float = this ?: 0F

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun String?.toDoubleOrZero(): Double = this?.toDoubleOrNull() ?: 0.0

fun String?.toFloatOrZero(): Float = this?.toFloatOrNull() ?: 0F

fun String?.toIntOrZero(): Int = this?.toInt() ?: 0

fun String?.toLongOrZero(): Long = this?.toLongOrNull() ?: 0L