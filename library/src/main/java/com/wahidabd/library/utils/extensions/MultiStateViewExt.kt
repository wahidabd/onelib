package com.wahidabd.library.utils.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.kennyc.view.MultiStateView
import com.wahidabd.library.R
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible

fun MultiStateView.setContentLayout(binding: ViewBinding) {
    this.addView(binding.root)
}

fun MultiStateView.setEmptyLayout(binding: ViewBinding) {
    this.setViewForState(binding.root, MultiStateView.ViewState.EMPTY, false)
}

fun MultiStateView.setErrorLayout(binding: ViewBinding) {
    this.setViewForState(binding.root, MultiStateView.ViewState.ERROR, false)
}

fun MultiStateView.setLoadingLayout(binding: ViewBinding) {
    this.setViewForState(binding.root, MultiStateView.ViewState.LOADING, false)
}

fun MultiStateView.showDefaultState() {
    viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showEmptyState(action: (View) -> Unit) {
    viewState = MultiStateView.ViewState.EMPTY
    val view = this.getView(MultiStateView.ViewState.EMPTY)
    view?.let { action.invoke(it) }
}

fun MultiStateView.showEmptyState(emptyMessage: String? = null, drawable: Drawable? = null, title: String? = null) {
    viewState = MultiStateView.ViewState.EMPTY
    val state = this.getView(MultiStateView.ViewState.EMPTY)

    if (emptyMessage != null){
        val error: TextView? = state?.findViewById(R.id.tv_error)
        if (error != null) error.text = emptyMessage
    }

    if (title != null){
        val tvTitle: TextView? = state?.findViewById(R.id.tv_title)
        if (tvTitle != null) tvTitle.text = title
    }

    if (drawable != null) {
        state?.findViewById<ImageView?>(R.id.img_error)?.setImageDrawable(drawable)
    }

}

fun MultiStateView.showErrorState(action: ((View) -> Unit)) {
    viewState = MultiStateView.ViewState.ERROR
    val view = getView(MultiStateView.ViewState.EMPTY)
    view?.let { action.invoke(view) }
}

fun MultiStateView.showErrorState(errorMessage: String? = null, title: String? = null, drawable: Drawable? = null, errorButton: Pair<String, () -> Unit>? = null) {
    viewState = MultiStateView.ViewState.ERROR
    val state = this.getView(MultiStateView.ViewState.ERROR)

    if (errorMessage != null){
        val error: TextView? = state?.findViewById(R.id.tv_error)
        if (error != null) error.text = errorMessage
    }

    if (title != null){
        val tvTitle: TextView? = state?.findViewById(R.id.tv_title)
        if (tvTitle != null) tvTitle.text = title
    }

    if (drawable != null) {
        state?.findViewById<ImageView?>(R.id.img_error)?.setImageDrawable(drawable)
    }

    if (errorButton != null){
        val btnError = state?.findViewById<Button>(R.id.btn_error)
        btnError?.apply {
            text = errorButton.first
            visible()
            onClick {
                errorButton.second.invoke()
            }
        }
    }
}

fun MultiStateView.showLoadingState() {
    viewState = MultiStateView.ViewState.LOADING
}