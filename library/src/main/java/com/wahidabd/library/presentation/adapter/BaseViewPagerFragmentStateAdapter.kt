package com.wahidabd.library.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BaseViewPagerFragmentStateAdapter<T>(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val items: List<T> = mutableListOf()

}