package com.wahidabd.library.utils.exts

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

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

fun View.visibleIf(condition: () -> Boolean){
    if (this.visibility != View.FOCUSABLES_ALL && condition.invoke()){
        this.visible()
    }
}

fun CompoundButton.check(){
    this.isChecked = true
}

fun CompoundButton.uncheck(){
    this.isChecked = false
}

fun EditText.onTextChange(doOnChange: (String) -> Unit){
    this.doAfterTextChanged {
        doOnChange.invoke(it.toString())
    }
}

fun TextInputLayout.onTextChange(doOnChange: (String) -> Unit){
    this.onTextChange {
        doOnChange.invoke(it)
    }
}