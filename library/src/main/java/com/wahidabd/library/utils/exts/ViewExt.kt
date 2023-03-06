package com.wahidabd.library.utils.exts

import android.view.View

fun View.onClick(action: (View) -> Unit) {
    this.setOnClickListener(action)
}

fun View.onLongClick(action: (View) -> Boolean) {
    this.setOnLongClickListener(action)
}