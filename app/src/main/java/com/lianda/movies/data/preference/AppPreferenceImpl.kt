package com.lianda.movies.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lianda.movies.utils.constants.AppConstants


class AppPreferenceImpl(context: Context):AppPreference{

    private val preference:SharedPreferences = context.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun storeString(key: String, value: String) {
      preference.edit().putString(key,value).apply()
    }

    override fun getString(key: String): String {
     return preference.getString(key, "").orEmpty()
    }

    override fun storeInt(key: String, value: Int) {
        preference.edit().putInt(key,value).apply()
    }

    override fun getInt(key: String): Int {
        return preference.getInt(key, 0)
    }

    override fun storeBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key,value).apply()
    }

    override fun getBoolean(key: String): Boolean {
       return preference.getBoolean(key, false)
    }

    override fun storeFloat(key: String, value: Float) {
        preference.edit().putFloat(key,value).apply()
    }

    override fun getFloat(key: String): Float {
        return preference.getFloat(key, 0.0f)
    }

    override fun <T> storeObject(key: String, value: T) {
        val gson = Gson()
        val json = gson.toJson(value)
        preference.edit().putString(key, json).apply()
    }

    override fun <T> getObject(key: String, cls: Class<T>): T {
        val gson = Gson()
        val json: String = preference.getString(key, "").orEmpty()
        return gson.fromJson(json, cls)
    }


}