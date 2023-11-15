//package com.wahidabd.library.presentation.adapter
//
//import android.content.Context
//import android.view.ViewGroup
//import androidx.viewbinding.ViewBinding
//import com.wahidabd.library.presentation.adapter.viewholder.BaseFilterableItemViewHolder
//import com.wahidabd.library.presentation.adapter.viewholder.BaseItemViewHolder
//
//
///**
// * Created by Wahid on 3/17/2023.
// * Github wahidabd.
// */
//
//abstract class BaseFilterableAdapter<T>(
//    private val context: Context,
//    private val data: List<T>,
//    private val onItemClickedListener: (T) -> Unit?
//) : BaseRecyclerAdapter<T, BaseItemViewHolder<T>>(context, ArrayList(data)) {
//
//
//    private val filteredData: MutableList<T> = ArrayList()
//    private val originalData: MutableList<T> = ArrayList()
//
//    abstract fun isFiltered(query: String, item: T): Boolean
//
//    fun filter(query: String){
//        filteredData.clear()
//        val tempList = ArrayList<T>()
//        tempList.clear()
//
//        for (item in originalData){
//            if (isFiltered(query, item)){
//                tempList.add(item)
//            }
//        }
//
//        filteredData.addAll(tempList)
//        this.clear()
//        this.addAll(if (query.isEmpty()) originalData else filteredData)
//        this.notifyDataSetChanged()
//    }
//
//    open fun addOrUpdate(items: MutableList<T>?){
//        super.addOrUpdate(items!!)
//        filteredData.clear()
//        filteredData.addAll(data)
//        originalData.clear()
//        originalData.addAll(data)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterableViewHolder =
//        FilterableViewHolder(
//            getViewBinding(parent, viewType))
//
//
//    inner class FilterableViewHolder(
//        binding: ViewBinding,
//    ): BaseItemViewHolder<T>(
//        context,
//        getViewBinding(parent, viewType),
//    ) {
//
//        open override fun bind(data: T) {}
//
//    }
//
//}