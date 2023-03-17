package com.wahidabd.library.utils.exts

import android.view.View
import android.widget.CompoundButton

fun View.onClick(action: (View) -> Unit) {
    this.setOnClickListener(action)
}

fun View.onLongClick(action: (View) -> Boolean) {
    this.setOnLongClickListener(action)
}

fun View.enable(){
    this.isEnabled = true
}
fun View.disable() {
    this.isEnabled = false
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun CompoundButton.check(){
    this.isChecked = true
}

fun CompoundButton.uncheck(){
    this.isChecked = false
}