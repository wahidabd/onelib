package com.wahidabd.onelibrary.di.features

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.onelibrary.data.movie.MovieDataStore
import com.wahidabd.onelibrary.data.movie.MovieRepository
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import com.wahidabd.onelibrary.data.movie.remote.MovieApiClient
import com.wahidabd.onelibrary.di.BASE_URL
import com.wahidabd.onelibrary.domain.movie.MovieInteractor
import com.wahidabd.onelibrary.domain.movie.MovieUseCase
import com.wahidabd.onelibrary.presentation.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {

    single {
        ApiService.createReactiveService(
            MovieApiClient::class.java,
            get(), get(named(BASE_URL))
        )
    }

    single { MovieApi(get()) }

    single <MovieRepository> { MovieDataStore(get()) }

    single <MovieUseCase> { MovieInteractor(get()) }

    viewModel { MovieViewModel(get(), get()) }
}