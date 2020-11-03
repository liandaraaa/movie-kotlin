package com.lianda.movies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lianda.movies.data.db.entities.CityEntity


@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city:CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities:List<CityEntity>)

    @Query("SELECT * FROM city")
    fun getAll(): List<CityEntity>
}