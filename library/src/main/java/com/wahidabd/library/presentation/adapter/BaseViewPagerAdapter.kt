package com.wahidabd.library.presentation.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * A generic abstract class that extends FragmentStateAdapter to manage fragments in a ViewPager within a FragmentActivity.
 *
 * @param T The type of items managed by the adapter.
 * @param fragment The FragmentActivity that hosts the ViewPager.
 *
 * This class provides the following functionality:
 * - Maintains a list of items of type T.
 * - Returns the number of items in the adapter.
 * - Allows adding a list of items to the adapter and notifies the adapter of the data change.
 * - Retrieves an item at a specified position.
 */
abstract class BaseViewPagerAdapter<T>(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val items = ArrayList<T>()

    /**
     * Returns the number of items in the adapter.
     *
     * @return The size of the items list.
     */
    override fun getItemCount(): Int = items.size

    /**
     * Adds a list of new items to the adapter, clears any existing items, and notifies the adapter of the data change.
     *
     * @param newItems A list of items of type T to add to the adapter.
     *
     * This function performs the following steps:
     * - Clears the current list of items.
     * - Adds all new items to the list.
     * - Notifies the adapter that the data set has changed.
     */
    fun addAllItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    /**
     * Retrieves an item at the specified position.
     *
     * @param position The position of the item to retrieve.
     * @return The item at the specified position, or null if the position is out of bounds.
     */
    fun getItem(position: Int): T? = items.getOrNull(position)
}
