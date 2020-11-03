package com.lianda.movies.domain.usecase.implementation

import com.lianda.movies.domain.entities.City
import com.lianda.movies.domain.repository.CityRepository
import com.lianda.movies.domain.usecase.contract.CityUseCase
import com.lianda.movies.utils.common.ResultState

class CityUseCaseImpl (private val repository: CityRepository):CityUseCase{
    override suspend fun fetchCity(): ResultState<List<City>> {
        val param = HashMap<String, String>()
        param["token"] = "noauth.android"

        return if (repository.isHasNetwork()){
            repository.fetchCity(param)
        }else{
            repository.fetchSavedCity()
        }
    }

}