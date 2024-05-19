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


abstract class BaseFilterableAdapter<T, H : BaseFilterableItemViewHolder<T>> :
    RecyclerView.Adapter<H>(), Filterable {

    private val differCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
            oldItem == newItem
    }

    private var originalData: List<T> = emptyList()
    private val listDiffer = AsyncListDiffer(this, differCallback)

    var setData: List<T>
        get() = listDiffer.currentList
        set(value) {
            originalData = value
            listDiffer.submitList(value)
        }

    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(setData[position])
    }

    override fun getItemCount(): Int = setData.size

    fun clear() {
        setData.toMutableList().clear()
    }

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