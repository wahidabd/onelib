package com.wahidabd.library.utils.exts

import com.wahidabd.library.presentation.adapter.Selectable

fun <T> T?.fallback(value: T): T =
    this ?: value

fun Any?.isNotNull(): Boolean = !isNull()

fun Any?.isNull(): Boolean = this == null

fun <T> List<T>?.toSelectable(): List<Selectable<T>> {
    return this?.map { Selectable(it) } ?: emptyList()
}

