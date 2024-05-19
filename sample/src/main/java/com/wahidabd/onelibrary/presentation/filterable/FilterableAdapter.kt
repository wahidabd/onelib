package com.wahidabd.onelibrary.presentation.filterable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseFilterableAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseFilterableItemViewHolder
import com.wahidabd.onelibrary.databinding.ItemRecyclerBinding


/**
 * Created by wahid on 5/18/2024.
 * Github github.com/wahidabd.
 */


class FilterableAdapter : BaseFilterableAdapter<String, FilterableAdapter.ViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterableAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseFilterableItemViewHolder<String>(binding) {
        override fun bind(data: String) = with(binding as ItemRecyclerBinding) {
            tvTitle.text = data
        }
    }
}