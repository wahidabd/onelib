package com.wahidabd.library.decoration.richview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wahidabd.library.utils.common.ContextProvider


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

abstract class BaseRichView : FrameLayout(ContextProvider.get()) {

    protected fun initView(context: Context, attrs: AttributeSet?){

    }

    abstract fun getLayout(): Int
    abstract fun bindView(view: View)

}