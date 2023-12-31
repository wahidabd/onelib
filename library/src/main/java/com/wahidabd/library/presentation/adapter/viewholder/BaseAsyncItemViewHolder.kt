package com.wahidabd.library.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class BaseAsyncItemViewHolder<T>(
    val binding: ViewBinding
) : ViewHolder(binding.root) {

    abstract fun bind(data: T)

}