package com.blissapplications.osm.errors

import com.blissapplications.osm.errors.utils.ProjError

class DatabaseCreateError(message: String?, throwable: Throwable?) :
    ProjError(message, throwable)