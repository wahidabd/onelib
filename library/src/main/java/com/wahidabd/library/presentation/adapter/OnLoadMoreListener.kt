package com.wahidabd.library.presentation.adapter

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */

interface OnLoadMoreListener {
    abstract fun onLoadMore(skip: Int, limit: Int, page: Int, totalItemsCount: Int, view: RecyclerView)
    abstract fun onLoadMoreRetryButtonClicked(skip: Int?, limit: Int?)
}