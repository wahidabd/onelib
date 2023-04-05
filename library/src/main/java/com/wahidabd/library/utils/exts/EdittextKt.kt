package com.wahidabd.library.utils.exts

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


fun EditText.toStringTrim() = this.text.toString().trim()

fun TextInputLayout.toStringTrim() = this.editText?.text.toString().trim()

fun EditText.clear() = this.text.clear()

fun TextInputLayout.clear() = this.editText?.text?.clear()