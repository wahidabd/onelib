package com.wahidabd.library.presentation.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.BaseView
import com.wahidabd.library.presentation.fragment.BaseFragment

abstract class BaseActivity <VB: ViewBinding> : AppCompatActivity(), BaseView, BaseFragment.Callback {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onReady()
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

    private fun onReady(){
        initUI()
        initAction()
        initProcess()
        initObservers()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    abstract fun initUI()
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObservers()

}
