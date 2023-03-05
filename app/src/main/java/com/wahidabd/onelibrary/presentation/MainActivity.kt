package com.wahidabd.onelibrary.presentation

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityMainBinding
import com.wahidabd.onelibrary.presentation.recycler.TestBaseRecyclerAdapter
import com.wahidabd.onelibrary.utils.Constant

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val recyclerAdapter: TestBaseRecyclerAdapter by lazy {
        TestBaseRecyclerAdapter(
            context = this,
            items = arrayListOf(),
            onItemClicked = {
                showToast(it.title)
            }
        )
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initAction() {
    }

    override fun initObservers() {
    }

    override fun initProcess() {
        recyclerAdapter.clear()
        recyclerAdapter.addOrUpdate(Constant.testData())
    }

    override fun initUI() {
        // set fragment with replace mode
//        setFragment(R.id.mainContainer, TestFragment(), true)

        binding.rvItem.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showToast(data: String){
        Snackbar.make(binding.root, data, Snackbar.LENGTH_SHORT).show()
        Log.d("CLICK", data)
    }

}