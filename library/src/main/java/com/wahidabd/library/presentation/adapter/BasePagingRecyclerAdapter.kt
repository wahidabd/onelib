package com.wahidabd.library.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


abstract class BasePagingRecyclerAdapter<T : Any, H : BaseAsyncItemViewHolder<T>> :
    PagingDataAdapter<T, H>(BasePagingComparator()) {

    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    override fun onBindViewHolder(holder: H, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H

}