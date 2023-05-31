package com.wahidabd.onelibrary.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.onelibrary.databinding.ItemRecyclerBinding

class TestBaseAsyncRecyclerAdapter(
    private val context: Context,
    items: ArrayList<String> = arrayListOf(),
    private val onItemClicked: ((String) -> Unit)?
) : BaseAsyncRecyclerAdapter<String, TestBaseAsyncRecyclerAdapter.ViewHolder>(items){

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemRecyclerBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(getViewBinding(parent, viewType))

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<String>(binding = binding){
        override fun bind(data: String) {
            with(binding as ItemRecyclerBinding){
                tvTitle.text = data
            }
        }

    }

}