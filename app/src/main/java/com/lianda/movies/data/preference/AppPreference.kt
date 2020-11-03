package com.lianda.movies.data.preference

interface AppPreference {
    fun storeString(key:String, value:String)
    fun getString(key: String):String

    fun storeInt(key:String, value:Int)
    fun getInt(key: String):Int

    fun storeBoolean(key:String, value:Boolean)
    fun getBoolean(key: String):Boolean

    fun storeFloat(key:String, value:Float)
    fun getFloat(key: String):Float

    fun <T> storeObject(key:String, value:T)
    fun <T> getObject(key: String, cls:Class<T>):T
}