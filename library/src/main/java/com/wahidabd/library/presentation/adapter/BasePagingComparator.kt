package com.wahidabd.library.presentation.adapter

import androidx.recyclerview.widget.DiffUtil


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class BasePagingComparator<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}