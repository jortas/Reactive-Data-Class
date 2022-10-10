package com.blissapplications.osm

import com.blissapplications.osm.utils.ProjUserAgent

actual class PlatformDependencies actual constructor() {
    actual val userAgent: ProjUserAgent = ProjUserAgent("iOS")
}