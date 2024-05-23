package com.wahidabd.library.decoration.onestateview


/**
 * Created by wahid on 5/23/2024.
 * Github github.com/wahidabd.
 */


/**
 * Extension function for OneStateView to set the view state to LOADING.
 */
fun OneStateView.showLoading() {
    viewState = OneStateView.OneViewState.LOADING
}

/**
 * Extension function for OneStateView to set the view state to ERROR.
 */
fun OneStateView.showError() {
    viewState = OneStateView.OneViewState.ERROR
}

/**
 * Extension function for OneStateView to set the view state to EMPTY.
 */
fun OneStateView.showEmpty() {
    viewState = OneStateView.OneViewState.EMPTY
}

/**
 * Extension function for OneStateView to set the view state to CONTENT.
 */
fun OneStateView.showContent() {
    viewState = OneStateView.OneViewState.CONTENT
}