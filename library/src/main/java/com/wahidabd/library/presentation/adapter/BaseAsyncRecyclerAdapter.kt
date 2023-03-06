package com.wahidabd.library.presentation.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder

abstract class BaseAsyncRecyclerAdapter<T, H : BaseAsyncItemViewHolder<T>>(
    private val data: List<T>
) : RecyclerView.Adapter<H>() {

    private val differCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

    }

    private val listDiffer = AsyncListDiffer(this, differCallback)

    var setData: List<T>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(setData[position])
    }

    override fun getItemCount(): Int = setData.size

}