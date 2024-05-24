package com.wahidabd.library.presentation.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseFilterableItemViewHolder


/**
 * Created by wahid on 5/18/2024.
 * Github github.com/wahidabd.
 */

/**
 * BaseFilterableAdapter is an abstract class for a RecyclerView adapter that supports filtering
 * and utilizes the AsyncListDiffer for efficient list updates. This adapter handles data of type
 * [T] and uses a custom view holder [H] that extends BaseFilterableItemViewHolder.
 *
 * @param T The type of data to be displayed in the RecyclerView.
 * @param H The type of ViewHolder used to bind the data.
 */

abstract class BaseFilterableAdapter<T, H : BaseFilterableItemViewHolder<T>> :
    RecyclerView.Adapter<H>(), Filterable {

    /**
     * DiffUtil.ItemCallback implementation to determine changes in the list and update only the
     * necessary items in the RecyclerView.
     */
    private val differCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem
    }

    private var originalData: List<T> = emptyList()
    private val listDiffer = AsyncListDiffer(this, differCallback)

    /**
     * Property to get and set the data in the adapter. Setting this property updates the original
     * data list and submits the list to AsyncListDiffer.
     */
    var setData: List<T>
        get() = listDiffer.currentList
        set(value) {
            originalData = value
            listDiffer.submitList(value)
        }

    /**
     * Abstract method to get the view binding for the ViewHolder. This must be implemented by the
     * subclass.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View.
     * @return The ViewBinding for the ViewHolder.
     */
    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    /**
     * Abstract method to create a new ViewHolder. This must be implemented by the subclass.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H

    /**
     * Binds the data at the specified position to the ViewHolder.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item
     *               at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(setData[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = setData.size

    /**
     * Clears the data in the adapter.
     */
    fun clear() {
        setData.toMutableList().clear()
    }

    /**
     * Returns a Filter that can be used to constrain data with a filtering pattern.
     *
     * @return A Filter instance for filtering the data.
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    originalData
                } else {
                    originalData.filter {
                        // Implement your filtering logic here
                        // For example, if T is a String:
                        // (it as String).contains(constraint, ignoreCase = true)

                        // Modify the condition as per your requirements
                        (it.toString().contains(constraint, ignoreCase = true))
                    }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(char: CharSequence?, results: FilterResults?) {
                listDiffer.submitList(results?.values as? List<T> ?: originalData)
            }

        }
    }

}