package com.wahidabd.onelibrary.presentation.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BasePagingRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.onelibrary.databinding.ItemRecyclerBinding
import com.wahidabd.onelibrary.domain.movie.model.Movie


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */

class TestPagingAdapter : BasePagingRecyclerAdapter<Movie, TestPagingAdapter.ViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Movie>(binding) {
        override fun bind(data: Movie) {
            with(binding as ItemRecyclerBinding) {
                tvTitle.text = data.title
            }
        }
    }

}