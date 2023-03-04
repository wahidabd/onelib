package com.wahidabd.library.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity <VB: ViewBinding> : AppCompatActivity() {

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

    abstract fun initAction()
    abstract fun initObservers()
    abstract fun initProcess()
    abstract fun initUI()

}
