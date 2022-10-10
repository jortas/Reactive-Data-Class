package com.blissapplications.osm.managers

import co.touchlab.kermit.Logger
import com.blissapplications.osm.DiskStorage
import com.blissapplications.osm.errors.UnknownError
import com.blissapplications.osm.model.requestModels.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsManager : KoinComponent, SettingsManagerBehavior {
    private val diskStorage: DiskStorage by inject()

    private val coroutineScope: CoroutineScope = MainScope()

    override suspend fun getOfficeId(): Result<Int> {
        return try {
            Result.of(diskStorage.getInteger("Office", 1))
        } catch (e: Exception) {
            Logger.e { e.message!! }
            Result.error(UnknownError(e))
        }
    }

    override suspend fun setOfficeId(id: Int): Result<Unit> {
        return try {
            Result.of(diskStorage.setInteger("Office", id))
        } catch (e: Exception) {
            Logger.e { e.message!! }
            Result.error(UnknownError(e))
        }
    }
}
