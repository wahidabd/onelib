package com.wahidabd.library.presentation.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BaseViewPagerFragmentStateAdapter<T>(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    private val items = ArrayList<T>()

    override fun getItemCount(): Int = items.size

    fun addAllItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): T? = items.getOrNull(position)

}