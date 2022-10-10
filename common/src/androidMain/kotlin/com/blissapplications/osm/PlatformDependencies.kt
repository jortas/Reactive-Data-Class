package com.blissapplications.osm

import co.touchlab.kermit.Logger
import com.blissapplications.osm.utils.ProjUserAgent

actual class PlatformDependencies actual constructor() {
    actual val userAgent: ProjUserAgent = ProjUserAgent("android")
}