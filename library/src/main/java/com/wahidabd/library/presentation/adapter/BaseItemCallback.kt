package com.wahidabd.library.presentation.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class BaseItemCallback : ItemCallback<DiffModel>() {

    override fun areItemsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean =
        oldItem.primaryKey == newItem.primaryKey

    override fun areContentsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean =
        oldItem.toString() == newItem.toString()

    override fun getChangePayload(oldItem: DiffModel, newItem: DiffModel): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

}