package com.lianda.movies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lianda.movies.data.db.dao.CityDao
import com.lianda.movies.data.db.entities.CityEntity
import com.lianda.movies.utils.constants.AppConstants.DATABASE_NAME

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun database(context: Context): AppDatabase {
            context.let {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }

    abstract fun orderDao():CityDao
}