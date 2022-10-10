package com.blissapplications.osm.managers

import com.blissapplications.osm.model.Office
import com.blissapplications.osm.model.requestModels.BookingRequest
import com.blissapplications.osm.model.requestModels.Result

interface SettingsManagerBehavior {
    suspend fun getOfficeId(): Result<Int>
    suspend fun setOfficeId(id: Int): Result<Unit>
}