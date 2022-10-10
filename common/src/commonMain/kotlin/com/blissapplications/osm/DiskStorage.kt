package com.blissapplications.osm

import com.blissapplications.osm.utils.ProjUserAgent

expect class DiskStorage() {
    fun init(arg1: Any)

    fun getString(key: String, defaultValue: String = ""): String
    fun setString(key: String, value: String)

    fun getInteger(key: String, defaultValue: Int = 0): Int
    fun setInteger(key: String, value: Int)
}