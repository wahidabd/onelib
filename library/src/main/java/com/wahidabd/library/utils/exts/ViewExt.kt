package com.wahidabd.library.utils.exts

import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

fun View.onClick(action: (View) -> Unit) {
    this.setOnClickListener(action)
}

fun View.onLongClick(action: (View) -> Boolean) {
    this.setOnLongClickListener(action)
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

inline fun View.onlyGoneIf(condition: () -> Boolean){
    if (condition.invoke()) gone()
    else visible()
}

inline fun View.onlyVisibleIf(condition: () -> Boolean){
    if (condition.invoke()) visible()
    else gone()
}

inline fun View.onlyInvisibleIf(condition: () -> Boolean){
    if (condition.invoke()) invisible()
    else visible()
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibleIf(condition: () -> Boolean) {
    if (this.visibility != View.FOCUSABLES_ALL && condition.invoke()) {
        this.visible()
    }
}

fun CompoundButton.check() {
    this.isChecked = true
}

fun CompoundButton.uncheck() {
    this.isChecked = false
}

fun EditText.onTextChange(doOnChange: (String) -> Unit) {
    this.doAfterTextChanged {
        doOnChange.invoke(it.toString())
    }
}

fun TextInputLayout.onTextChange(doOnChange: (String) -> Unit) {
    this.editText?.doAfterTextChanged {
        doOnChange.invoke(it.toString())
    }
}

inline fun View.disableIf(condition: () -> Boolean){
    if (condition.invoke()) disable()
    else enable()
}

inline fun View.enableIf(condition: () -> Boolean){
    if (condition.invoke()) enable()
    else disable()
}

fun View.setHeight(value: Int){
    val params = this.layoutParams
    params.height = value
    this.layoutParams = params
}

fun View.setWidth(value: Int){
    val params = this.layoutParams
    params.width = value
    this.layoutParams = params
}

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int){
    if (this.layoutParams is ViewGroup.MarginLayoutParams){
        val params= this.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(left, top, right, bottom)
        this.requestLayout()
    }
}