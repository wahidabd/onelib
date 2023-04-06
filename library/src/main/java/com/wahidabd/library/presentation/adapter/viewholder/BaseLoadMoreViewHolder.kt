package com.wahidabd.library.presentation.adapter.viewholder

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseRecyclerAdapter.OnItemClickListener
import com.wahidabd.library.presentation.adapter.BaseRecyclerAdapter.OnLongItemClickListener
import com.wahidabd.library.presentation.adapter.OnLoadMoreListener


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class BaseLoadMoreViewHolder<T>(
    context: Context?,
    viewBinding: ViewBinding,
    itemClickListener: OnItemClickListener?,
    longItemClickListener: OnLongItemClickListener?,
    val loadMoreListener: OnLoadMoreListener?,
    val loading: Boolean,
    val loadMoreSkip: Int,
    val loadMoreLimit: Int
) : BaseItemViewHolder<T>(context, viewBinding, itemClickListener, longItemClickListener) {
    override fun bind(data: T) {
        TODO("Not yet implemented")
    }
}