package com.wahidabd.library.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

abstract class BaseSelectableAdapter<T>(
    private val mContext: Context,
    private val items: List<Selectable<T>>,
    private val isMultipleSelect: Boolean,
    private val onItemClickedListener: (Selectable<T>) -> Unit?
) : BaseRecyclerAdapter<Selectable<T>, BaseSelectableAdapter<T>.SelectableViewHolder>(mContext, ArrayList(items)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder =
        SelectableViewHolder(
            mContext,
            getViewBinding(parent, viewType),
            onItemClickListener,
            onLongItemClickListener
        )

    inner class SelectableViewHolder(
        context: Context,
        binding: ViewBinding,
        itemClickListener: OnItemClickListener?,
        longItemClickListener: OnLongItemClickListener?
    ) : BaseItemViewHolder<Selectable<T>>(context, binding, itemClickListener, longItemClickListener) {

        @SuppressLint("NotifyDataSetChanged")
        override fun bind(data: Selectable<T>) {
            binding.root.onClick {
                if (isMultipleSelect) data.selected = !data.selected
                else data.selected = true

                if (!isMultipleSelect){
                    var index = 0
                    val iterable = items.iterator()
                    while (iterable.hasNext()){
                        val item = iterable.next()
                        index++
                        if (index < 0) throw ArithmeticException("Index overflow has happened")

                        item.selected = index == this.adapterPosition
                    }
                }

                onItemClickedListener.invoke(data)
                notifyDataSetChanged()
            }
        }

    }

}