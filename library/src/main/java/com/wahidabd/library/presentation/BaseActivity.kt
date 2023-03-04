package com.wahidabd.library.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity <VB: ViewBinding> : AppCompatActivity(), BaseView, BaseFragment.Callback {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        initUI()
        initProcess()
        initObservers()
        initAction()
    }

    fun setFragment(viewRes: Int, fragment: Fragment, addToBackStack: Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(viewRes, fragment)
            if (addToBackStack) addToBackStack(null)
            commit()
        }
    }

    override fun finishActivity() {
        this.finish()
    }

    override fun setupToolbar(title: String, isChild: Boolean) {
        val actionBar = this.supportActionBar
        if (actionBar != null){
            actionBar.title = title
            actionBar.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    override fun setupToolbar(toolbar: Toolbar?, isChild: Boolean) {
        if (toolbar != null) setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(isChild)
    }

    override fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean) {
        if (toolbar != null) setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(isChild)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    abstract fun initAction()
    abstract fun initObservers()
    abstract fun initProcess()
    abstract fun initUI()

}
