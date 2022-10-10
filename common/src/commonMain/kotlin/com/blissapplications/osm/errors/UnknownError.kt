package com.blissapplications.osm.errors

import com.blissapplications.osm.errors.utils.ProjError

class UnknownError(message: String?, throwable: Throwable?) :
    ProjError(message, throwable) {
    constructor(throwable: Throwable?) : this(throwable?.message, throwable)
}