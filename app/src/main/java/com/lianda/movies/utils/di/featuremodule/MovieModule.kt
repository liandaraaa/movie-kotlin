package com.lianda.movies.utils.di.featuremodule

import com.lianda.movies.domain.repository.MovieRepository
import com.lianda.movies.data.repository.MovieRepositoryImpl
import com.lianda.movies.domain.usecase.contract.MovieUseCase
import com.lianda.movies.domain.usecase.implementation.MovieUseCaseImpl
import com.lianda.movies.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            get()
        )
    }

    single<MovieUseCase> { MovieUseCaseImpl(get()) }

    viewModel { MovieViewModel(get()) }
}