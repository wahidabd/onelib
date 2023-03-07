package com.wahidabd.library.utils.exts

fun <T> T?.fallback(value: T): T =
    this ?: value