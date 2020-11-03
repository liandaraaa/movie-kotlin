package com.lianda.movies.utils.di

import com.lianda.movies.data.preference.AppPreference
import com.lianda.movies.data.preference.AppPreferenceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single<AppPreference> { AppPreferenceImpl(androidContext()) }
}