package com.wahidabd.library.utils.exts

import android.view.Window

fun Window.clearFull() {
    this.setFlags(512, 512)
}

fun Window.setFull() {
    this.clearFlags(512)
}