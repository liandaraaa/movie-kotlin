package com.lianda.movies.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lianda.movies.domain.entities.City

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey
    val id:String,
    val name:String
){

    fun toCity():City{
        return City(
            id = id,
            name = name
        )
    }
}