package com.wahidabd.library.presentation.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder

/**
 * Created by wahid on 5/18/2024.
 * Github: github.com/wahidabd
 *
 * BaseAsyncRecyclerAdapter is an abstract class that provides a base implementation for
 * a RecyclerView.Adapter utilizing AsyncListDiffer for efficient list updates and
 * automatic diffing. It simplifies the process of handling list data and binding view holders.
 *
 * @param T The type of the items in the list.
 * @param H The type of the ViewHolder which extends BaseAsyncItemViewHolder.
 */
abstract class BaseAsyncRecyclerAdapter<T, H : BaseAsyncItemViewHolder<T>> :
    RecyclerView.Adapter<H>() {

    private val differCallback = object : DiffUtil.ItemCallback<T>() {
        /**
         * Called to check whether two objects represent the same item.
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the two items represent the same object or false if they are different.
         */
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem

        /**
         * Called to check whether two items have the same data.
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the contents of the items are the same, false otherwise.
         */
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, differCallback)

    /**
     * A property for getting and setting the list data.
     * When setting new data, the list is submitted to AsyncListDiffer which calculates the
     * difference between the old list and the new list in the background.
     */
    var setData: List<T>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    /**
     * Abstract method to be implemented by subclasses to provide the view binding for a ViewHolder.
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return The ViewBinding instance for the ViewHolder.
     */
    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    /**
     * Abstract method to be implemented by subclasses to create a new ViewHolder.
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return The ViewHolder instance.
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the item.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(setData[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = setData.size

    fun clear() {
        setData.toMutableList().clear()
    }

}