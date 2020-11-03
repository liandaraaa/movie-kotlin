package com.lianda.movies.domain.usecase.contract

import com.lianda.movies.domain.entities.City
import com.lianda.movies.utils.common.ResultState

interface CityUseCase {

    suspend fun fetchCity(): ResultState<List<City>>
}