package com.wahidabd.onelibrary.presentation.movie


import androidx.lifecycle.lifecycleScope
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.onelibrary.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieActivity : BaseActivity<ActivityMainBinding>() {

    private val movieViewModel: MovieViewModel by viewModel()


    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
        Timber.d("INIT: UI")
    }

    override fun initAction() {
        Timber.d("INIT: ACTION")
    }

    override fun initProcess() {
        movieViewModel.detail(676)
        Timber.d("INIT: PROCESS")
    }

    override fun initObservers() {
        Timber.d("INIT: OBSERVERS")
        movieViewModel.detail.observerLiveData(this,
            onLoading = {
                Timber.d("LOADING")
            },
            onEmpty = {
                Timber.d("EMPTY")
            },
            onFailure = {throwable, message ->
                Timber.e("ERROR: $throwable --> $message")
            },
            onSuccess = {
                Timber.d("SUCCESS: $it")
            }
        )
    }

}