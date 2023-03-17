package com.wahidabd.library.utils.common

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextProvider {

    val INSTANCE = ContextProvider
    private lateinit var mContext: Context

    fun get(): Context = INSTANCE.getContext()

    private fun getContext(): Context {
        return mContext
    }

    private fun init(context: Context) {
        mContext = context
    }

    fun initialize(context: Context) {
        INSTANCE.init(context)
    }

}