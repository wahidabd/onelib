package com.wahidabd.onelibrary.presentation.viewpager

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.presentation.adapter.BaseViewPagerAdapter
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.fallback
import com.wahidabd.onelibrary.databinding.ActivityViewPagerBinding

class ViewPagerActivity : BaseActivity<ActivityViewPagerBinding>() {

    private val viewPager by lazy {
        object : BaseViewPagerAdapter<BaseFragment<*>>(this){
            override fun createFragment(position: Int): Fragment =
                getItem(position).fallback(Fragment())
        }
    }

    override fun getViewBinding(): ActivityViewPagerBinding =
        ActivityViewPagerBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        setupViewPager()
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun setupViewPager() {
        val titles = listOf("First Screen", "Second Screen")
        binding.vpScreen.apply {
            adapter = viewPager
            isSaveEnabled = false
            isUserInputEnabled = false

            TabLayoutMediator(binding.tabLayout, binding.vpScreen) {tab, position ->
                tab.text = titles[position]
            }.attach()
        }

        viewPager.addAllItems(
            listOf(
                FirstViewPagerFragment(),
                SecondViewPagerFragment()
            )
        )
    }

}