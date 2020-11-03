package com.lianda.movies.domain.repository

import com.lianda.movies.domain.entities.City
import com.lianda.movies.utils.common.ResultState

interface CityRepository {

    fun isHasNetwork():Boolean

    fun isLogin():Boolean

    fun retrieveToken() : String

    suspend fun fetchCity(param:HashMap<String,String>) : ResultState<List<City>>

    suspend fun fetchSavedCity() : ResultState<List<City>>

}