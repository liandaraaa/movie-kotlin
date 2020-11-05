package com.lianda.movies.base

import android.app.Application
import com.lianda.movies.utils.di.apiModule
import com.lianda.movies.utils.di.featuremodule.movieModule
import com.lianda.movies.utils.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    movieModule
                )
            )
        }
    }

}