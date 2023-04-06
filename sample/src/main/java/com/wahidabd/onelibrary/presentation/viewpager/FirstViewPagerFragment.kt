package com.wahidabd.onelibrary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.onelibrary.databinding.FragmentFirstViewPagerBinding
import com.wahidabd.onelibrary.presentation.movie.MovieViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber

class FirstViewPagerFragment : BaseFragment<FragmentFirstViewPagerBinding>() {

    private val viewModel: MovieViewModel by inject()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentFirstViewPagerBinding =
        FragmentFirstViewPagerBinding.inflate(layoutInflater)

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        viewModel.getMovies()
    }

    override fun initObservers() {
        viewModel.movies.observerLiveData(viewLifecycleOwner,
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
                Timber.d("SUCCESS: ${it.first}")
            }
        )
    }

}