package com.wahidabd.onelibrary.presentation

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityMainBinding
import com.wahidabd.onelibrary.domain.base.TestModel
import com.wahidabd.onelibrary.presentation.adapter.AsyncRecyclerActivity
import com.wahidabd.onelibrary.presentation.adapter.TestBaseRecyclerAdapter
import com.wahidabd.onelibrary.presentation.bottomsheet.BottomSheetActivity
import com.wahidabd.onelibrary.presentation.dialog.DialogActivity
import com.wahidabd.onelibrary.presentation.filterable.FilterableActivity
import com.wahidabd.onelibrary.presentation.firestore.FirestoreActivity
import com.wahidabd.onelibrary.presentation.movie.MovieActivity
import com.wahidabd.onelibrary.presentation.multistateview.MultiStateViewActivity
import com.wahidabd.onelibrary.presentation.notification.NotificationActivity
import com.wahidabd.onelibrary.presentation.paging.PagingActivity
import com.wahidabd.onelibrary.presentation.realtime.RealtimeActivity
import com.wahidabd.onelibrary.presentation.rxdatabase.NoteActivity
import com.wahidabd.onelibrary.presentation.selectable.SelectableAdapterActivity
import com.wahidabd.onelibrary.presentation.validation.PassiveValidationActivity
import com.wahidabd.onelibrary.presentation.validation.ReactiveValidationActivity
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

    override fun initIntent() {}

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
        // setFragment(R.id.mainContainer, TestFragment(), true)

        binding.rvItem.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showToast(data: String) {
        Snackbar.make(binding.root, data, Snackbar.LENGTH_SHORT).show()
    }

    private fun navigate(data: TestModel) {
        when (data.id) {
            2 -> startActivity(Intent(this, AsyncRecyclerActivity::class.java))
            3 -> startActivity(Intent(this, ViewPagerActivity::class.java))
            4 -> startActivity(Intent(this, MovieActivity::class.java))
            5 -> NotificationActivity.start(this)
            6 -> NoteActivity.start(this)
            7 -> MultiStateViewActivity.start(this)
            8 -> PagingActivity.start(this)
            9 -> FirestoreActivity.start(this)
            10 -> RealtimeActivity.start(this)
            11 -> PassiveValidationActivity.start(this)
            12 -> ReactiveValidationActivity.start(this)
            13 -> SelectableAdapterActivity.start(this)
            14 -> BottomSheetActivity.start(this)
            15 -> FilterableActivity.start(this)
            16 -> DialogActivity.start(this)
        }
    }

    companion object {
        @SuppressLint("UnspecifiedImmutableFlag")
        fun pendingIntent(context: Context): PendingIntent =
            PendingIntent.getActivity(
                context, 0,
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )
    }

}