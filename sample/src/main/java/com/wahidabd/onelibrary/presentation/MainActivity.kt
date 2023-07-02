package com.wahidabd.onelibrary.presentation

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.domain.base.TestModel
import com.wahidabd.onelibrary.databinding.ActivityMainBinding
import com.wahidabd.onelibrary.presentation.adapter.AsyncRecyclerActivity
import com.wahidabd.onelibrary.presentation.adapter.TestBaseRecyclerAdapter
import com.wahidabd.onelibrary.presentation.firestore.FirestoreActivity
import com.wahidabd.onelibrary.presentation.imagepicker.ImagePickerActivity
import com.wahidabd.onelibrary.presentation.movie.MovieActivity
import com.wahidabd.onelibrary.presentation.multistateview.MultiStateViewActivity
import com.wahidabd.onelibrary.presentation.notification.NotificationActivity
import com.wahidabd.onelibrary.presentation.paging.PagingActivity
import com.wahidabd.onelibrary.presentation.permission.PermissionActivity
import com.wahidabd.onelibrary.presentation.realtime.RealtimeActivity
import com.wahidabd.onelibrary.presentation.rxdatabase.NoteActivity
import com.wahidabd.onelibrary.presentation.viewpager.ViewPagerActivity
import com.wahidabd.onelibrary.utils.Constant

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val recyclerAdapter: TestBaseRecyclerAdapter by lazy {
        TestBaseRecyclerAdapter(
            context = this,
            items = arrayListOf(),
            onItemClicked = {
                navigate(it)
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
    }

    private fun navigate(data: TestModel){
        when(data.id){
            2 -> startActivity(Intent(this, AsyncRecyclerActivity::class.java))
            3 -> startActivity(Intent(this, ViewPagerActivity::class.java))
            4 -> startActivity(Intent(this, MovieActivity::class.java))
            5 -> ImagePickerActivity.start(this)
            6 -> PermissionActivity.start(this)
            7 -> NotificationActivity.start(this)
            8 -> NoteActivity.start(this)
            9 -> MultiStateViewActivity.start(this)
            10 -> PagingActivity.start(this)
            11 -> FirestoreActivity.start(this)
            12 -> RealtimeActivity.start(this)
        }
    }

    companion object {
        @SuppressLint("UnspecifiedImmutableFlag")
        fun pendingIntent(context: Context): PendingIntent =
            PendingIntent.getActivity(
                context, 0,
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }, PendingIntent.FLAG_ONE_SHOT
            )
    }

}