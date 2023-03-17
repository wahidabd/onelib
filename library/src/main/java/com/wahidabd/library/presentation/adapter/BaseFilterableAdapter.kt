package com.wahidabd.library.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseItemViewHolder


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

abstract class BaseFilterableAdapter<T>(
    private val mContext: Context,
    private val data: List<T>,
    private val onItemClickedListener: (T) -> Unit?
) : BaseRecyclerAdapter<T, BaseFilterableAdapter<T>.FilterableViewHolder>(mContext, ArrayList(data)) {


    private val filteredData: MutableList<T> = ArrayList()
    private val originalData: MutableList<T> = ArrayList()

    fun addOrUpdate(items: MutableList<T>?){
        super.addOrUpdate(items!!)
        filteredData.clear()
        filteredData.addAll(data)
        originalData.clear()
        originalData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterableViewHolder =
        FilterableViewHolder(
            mContext,
            getViewBinding(parent, viewType),
            onItemClickListener,
            onLongItemClickListener
        )


    inner class FilterableViewHolder(
        context: Context,
        binding: ViewBinding,
        onItemClickListener: OnItemClickListener?,
        longItemClickListener: OnLongItemClickListener?
    ): BaseItemViewHolder<T>(context, binding, onItemClickListener, longItemClickListener) {

        override fun bind(data: T) {}

    }

}