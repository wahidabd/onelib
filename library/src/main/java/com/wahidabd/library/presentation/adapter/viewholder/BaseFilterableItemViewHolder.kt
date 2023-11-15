package com.wahidabd.library.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding


/**
 * Created by Wahid on 10/24/2023.
 * Github github.com/wahidabd.
 */


abstract class BaseFilterableItemViewHolder<T>(
    val binding: ViewBinding
) : ViewHolder(binding.root){

    abstract fun bind(data: T)

}