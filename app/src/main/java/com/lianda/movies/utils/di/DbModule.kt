package com.lianda.movies.utils.di

import com.lianda.movies.data.db.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.database(get()) }

    single { get<AppDatabase>().orderDao() }
}