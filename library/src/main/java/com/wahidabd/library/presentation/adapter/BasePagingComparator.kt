package com.wahidabd.library.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class BasePagingComparator<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }
}