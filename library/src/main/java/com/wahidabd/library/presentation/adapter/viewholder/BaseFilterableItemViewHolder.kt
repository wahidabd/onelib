package com.wahidabd.library.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding


/**
 * Created by Wahid on 10/24/2023.
 * Github github.com/wahidabd.
 */

/**
 * BaseAsyncItemViewHolder is an abstract base class for RecyclerView.ViewHolder that uses ViewBinding.
 * This class provides a standard way to bind data asynchronously to the view holder.
 *
 * @param T The type of the data to be bound to the ViewHolder.
 * @param binding The ViewBinding object associated with the ViewHolder's layout.
 * @constructor Creates a ViewHolder with the specified ViewBinding.
 *
 * @property binding The ViewBinding instance used to access views in the ViewHolder's layout.
 */
abstract class BaseFilterableItemViewHolder<T>(
    val binding: ViewBinding
) : ViewHolder(binding.root){

    /**
     * Abstract method to bind data to the ViewHolder's view.
     * Subclasses must implement this method to define how data of type T should be bound to the views.
     *
     * @param data The data item of type T to be bound to the ViewHolder.
     */
    abstract fun bind(data: T)

}