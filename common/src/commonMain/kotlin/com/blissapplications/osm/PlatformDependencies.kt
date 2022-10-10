package com.blissapplications.osm

import com.blissapplications.osm.utils.ProjUserAgent

expect class PlatformDependencies() {
    val userAgent: ProjUserAgent
}