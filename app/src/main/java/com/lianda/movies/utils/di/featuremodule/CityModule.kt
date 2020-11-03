package com.lianda.movies.utils.di.featuremodule

import com.lianda.movies.domain.repository.CityRepository
import com.lianda.movies.data.repository.CityRepositoryImpl
import com.lianda.movies.domain.usecase.contract.CityUseCase
import com.lianda.movies.domain.usecase.implementation.CityUseCaseImpl
import com.lianda.movies.presentation.viewmodel.CityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cityModule = module {
    single<CityRepository> {
        CityRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<CityUseCase> { CityUseCaseImpl(get()) }

    viewModel { CityViewModel(get()) }
}