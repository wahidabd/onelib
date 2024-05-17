package com.wahidabd.library.presentation.adapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.utils.SelectedItem
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

abstract class BaseSelectableAdapter<T>(
    private val isMultipleSelect: Boolean = false,
    private val selectableItem: (selected: T) -> Unit,
) : BaseAsyncRecyclerAdapter<Selectable<T>, BaseSelectableAdapter<T>.BaseSelectableViewHolder>() {

    private var currentSelectedIndex = -1

    abstract fun bindData(binding: ViewBinding, data: Selectable<T>)
    abstract fun setSelected(binding: ViewBinding, selected: Boolean)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSelectableViewHolder {
        return BaseSelectableViewHolder(getViewBinding(parent, viewType))
    }

    inner class BaseSelectableViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<Selectable<T>>(binding), SelectedItem {

        override fun bind(data: Selectable<T>) {
            setSelectedItem(data.selected)

            binding.root.onClick {
                val previousIndex = currentSelectedIndex
                currentSelectedIndex = bindingAdapterPosition

                if (isMultipleSelect) {
                    data.selected = !data.selected
                    setSelectedItem(data.selected)
                } else {
                    if (currentSelectedIndex != previousIndex) {
                        data.selected = true

                        if (previousIndex > -1) {
                            setData[previousIndex].selected = false
                            notifyItemChanged(previousIndex)
                        }

                        setSelectedItem(data.selected)
                    }
                }

                selectableItem.invoke(data.item)
            }

            bindData(binding, data)
        }

        override fun setSelectedItem(selected: Boolean) {
            setSelected(binding, selected)
            if (selected) currentSelectedIndex = bindingAdapterPosition
        }
    }
}