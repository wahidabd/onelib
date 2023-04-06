package com.wahidabd.library.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.wahidabd.library.presentation.adapter.Pager

abstract class BaseDiffUtil <T> (val oldItem: List<Pager<T>>, val newItem: List<Pager<T>>) : DiffUtil.Callback() {

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