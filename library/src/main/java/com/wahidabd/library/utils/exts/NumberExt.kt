package com.wahidabd.library.utils.exts

fun Int?.orZero(maxLength: Int): String = (this ?: 0) as String

fun Number?.isNotZero(): Boolean = this != 0

fun Double?.orZero(): Double {
    TODO()
}

fun Float?.orZero(): Float {
    TODO()
}

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun String?.toDoubleOrZero(): Double {
    TODO()
}

fun String?.toFloatOrZero(): Float {
    TODO()
}

fun String?.toIntOrZero(): Int {
    TODO()
}

fun String?.toLongOrZero(): Long {
    TODO()
}