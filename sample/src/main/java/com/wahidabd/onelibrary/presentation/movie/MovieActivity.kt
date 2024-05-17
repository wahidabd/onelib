package com.wahidabd.onelibrary.presentation.movie


import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.onelibrary.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieActivity : BaseActivity<ActivityMainBinding>() {

    private val movieViewModel: MovieViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
        movieViewModel.detail(676)
    }

    override fun initObservers() {
        movieViewModel.detail.observerLiveData(this,
            onLoading = {
                Timber.d("LOADING")
            },
            onEmpty = {
                Timber.d("EMPTY")
            },
            onFailure = { message ->
                Timber.e("ERROR: $message")
            },
            onSuccess = {
                Timber.d("SUCCESS: $it")
            }
        )
    }

}