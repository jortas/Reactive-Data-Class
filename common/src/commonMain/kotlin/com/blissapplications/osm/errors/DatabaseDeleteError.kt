package com.blissapplications.osm.errors

import com.blissapplications.osm.errors.utils.ProjError

class DatabaseDeleteError(message: String?, throwable: Throwable?) :
    ProjError(message, throwable)