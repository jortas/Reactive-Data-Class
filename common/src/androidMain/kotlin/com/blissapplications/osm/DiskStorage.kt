package com.blissapplications.osm

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences

actual class DiskStorage actual constructor() {
    lateinit var sharedPreferences: SharedPreferences

    actual fun init(arg1: Any) {
        val contextWrapper = arg1 as ContextWrapper
        sharedPreferences =
            contextWrapper.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    }

    actual fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    actual fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    actual fun getInteger(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    actual fun setInteger(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }
}