package com.wahidabd.library.utils.exts

fun <T> T?.fallback(value: T): T =
    this ?: value

fun Any?.isNotNull(): Boolean = !isNull()

fun Any?.isNull(): Boolean = this == null

