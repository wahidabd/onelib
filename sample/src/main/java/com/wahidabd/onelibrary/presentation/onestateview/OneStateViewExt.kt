package com.wahidabd.onelibrary.presentation.onestateview

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.onestateview.OneStateView
import com.wahidabd.onelibrary.databinding.LayoutErrorBinding


/**
 * Created by wahid on 5/23/2024.
 * Github github.com/wahidabd.
 */


fun OneStateView.showCustomErrorState(
    message: String = emptyString(),
    onRetry: (() -> Unit)? = null
) {
    viewState = OneStateView.OneViewState.ERROR
    val binding = LayoutErrorBinding.inflate(this.context.layoutInflater)
    this.setViewForState(binding.root, OneStateView.OneViewState.ERROR, false)

    if (message.isNotEmpty()) {
        binding.tvError.text = message
    }

    binding.btnError.onClick {
        onRetry?.invoke()
    }
}