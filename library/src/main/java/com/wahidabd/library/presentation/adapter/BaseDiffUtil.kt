package com.wahidabd.library.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtil <T> (val oldItem: List<Pager<T>>, val newItem: List<Pager<T>>) : DiffUtil.Callback() {

    fun getOldItemSize(): Int = oldItem.size
    fun getNewItemSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] == newItem[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] == newItem[newItemPosition]

    fun getChangePayload() =
        listOf(PayloadKey.VALUE)

    enum class PayloadKey {
        VALUE
    }
}