package com.wahidabd.library.validation.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.wahidabd.library.R
import com.wahidabd.library.decoration.richview.BaseRichView
import com.wahidabd.library.presentation.view.ErrorableView
import com.wahidabd.library.presentation.view.InputView
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.invisible


/**
 * Created by Wahid on 5/13/2023.
 * Github github.com/wahidabd.
 */


class ValidationAutoCompleteTextView(
    private val context: Context,
    private val attributeSet: AttributeSet
): BaseRichView(), ErrorableView, InputView{

    private lateinit var autoCompleteTextView: AppCompatAutoCompleteTextView
    private lateinit var tvError: TextView
    private var errorMessageColor: ColorStateList? = null
    private var hint: String? = emptyString()
    private var errorMessage: String? = emptyString()
    private var size: Float? = null

    fun initView(){
        parseAttribute(attributeSet)
    }

    override fun getLayout(): Int {
        return R.layout.validation_auto_complete_text_view
    }

    override fun bindView(view: View) {
        autoCompleteTextView = view.findViewById(R.id.auto_complete_text_view)
        tvError = view.findViewById(R.id.tvError)
    }

    override fun hideError() {
        tvError.invisible()
    }

    override fun isErrorShowing(): Boolean {
        return tvError.visibility == View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        tvError.text = errorMessage
    }

    override fun getText(): String {
        return autoCompleteTextView.text.toString()
    }

    private fun parseAttribute(attrs: AttributeSet?){
        parseAttrs(attrs)
        setHint()
    }

    private fun parseAttrs(attrs: AttributeSet?){
//        val attribute = context.obtainStyledAttributes(attrs, R.styleable.OneValidationAutoCompleteTextView)
//        errorMessage = attribute.getString(R.styleable.OneValidationAutoCompleteTextView_actv_error_message)
//        hint = attribute.getString(R.styleable.OneValidationAutoCompleteTextView_actv_hint)
//        errorMessageColor = attribute.getColorStateList(R.styleable.OneValidationAutoCompleteTextView_actv_error_message_color)
//        size = attribute.getDimension(R.styleable.OneValidationAutoCompleteTextView_actv_error_message_size, resources.getDimension(R.dimen.text_size_12sp))
//        attribute.recycle()
    }

    private fun setHint(){
        autoCompleteTextView.hint = this.hint
    }

}