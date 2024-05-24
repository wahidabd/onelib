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

/**
 * BaseSelectableAdapter is an abstract class that extends BaseAsyncRecyclerAdapter.
 * This adapter allows for selectable items within a RecyclerView.
 * It supports both single and multiple selection modes.
 *
 * @param T The type of the data being handled by the adapter.
 * @param isMultipleSelect A boolean flag to indicate if multiple selection is allowed.
 * @param selectableItem A lambda function to handle the selected item.
 */
abstract class BaseSelectableAdapter<T>(
    private val isMultipleSelect: Boolean = false,
    private val selectableItem: (selected: T) -> Unit,
) : BaseAsyncRecyclerAdapter<Selectable<T>, BaseSelectableAdapter<T>.BaseSelectableViewHolder>() {

    private var currentSelectedIndex = -1

    /**
     * Abstract method to bind data to the ViewHolder.
     * @param binding The ViewBinding object associated with the ViewHolder.
     * @param data The data to bind to the ViewHolder.
     */
    abstract fun bindData(binding: ViewBinding, data: Selectable<T>)

    /**
     * Abstract method to set the selection state of the item.
     * @param binding The ViewBinding object associated with the ViewHolder.
     * @param selected The selection state of the item.
     */
    abstract fun setSelected(binding: ViewBinding, selected: Boolean)


    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSelectableViewHolder {
        return BaseSelectableViewHolder(getViewBinding(parent, viewType))
    }


    /**
     * Inner class that represents a ViewHolder with selectable items.
     * @param binding The ViewBinding object associated with the ViewHolder.
     */
    inner class BaseSelectableViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<Selectable<T>>(binding), SelectedItem {


        /**
         * Binds the given data to the ViewHolder.
         * @param data The data to bind to the ViewHolder.
         */
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


        /**
         * Sets the selection state of the item.
         * @param selected The selection state of the item.
         */
        override fun setSelectedItem(selected: Boolean) {
            setSelected(binding, selected)
            if (selected) currentSelectedIndex = bindingAdapterPosition
        }
    }
}