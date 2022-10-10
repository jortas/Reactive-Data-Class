package com.blissapplications.osm.managers

import co.touchlab.kermit.Logger
import com.blissapplications.osm.errors.utils.ErrorMapper
import com.blissapplications.osm.model.*
import com.blissapplications.osm.model.requestModels.BookingRequest
import com.blissapplications.osm.model.requestModels.Result
import com.blissapplications.osm.model.responseModels.RegisterUserResponse
import com.blissapplications.osm.remote.BookingApi
import com.blissapplications.osm.remote.OfficeApi
import com.blissapplications.osm.remote.UserApi
import io.ktor.client.plugins.*

import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BlissDeskManager : KoinComponent, BlissDeskManagerBehavior {
    private val bookingApi: BookingApi by inject()
    private val officeApi: OfficeApi by inject()
    private val userApi: UserApi by inject()

    private val coroutineScope: CoroutineScope = MainScope()
    private var list: List<DayAvailability> = emptyList()

    override suspend fun bookSeat(
        token: String,
        bookingRequest: BookingRequest
    ): Result<Booking>  {
        Logger.i { "> booking seat | token: $token, bookingRequest: $bookingRequest" }
        return try {
            Result.of(bookingApi.bookSeat(token, bookingRequest))
        } catch (e: ResponseException) {
            Logger.e { e.message!! }
            Result.error(ErrorMapper.mapToProjError(e))
        }
    }

    override suspend fun deleteBooking(token: String, bookingId: Int): Result<HttpResponse> {
        Logger.i { "> deleting booking | token: $token, bookingId: $bookingId" }
        return try {
            Result.of(bookingApi.deleteBooking(token, bookingId))
        } catch (e: ResponseException) {
            Logger.e { e.message!! }
            Result.error(ErrorMapper.mapToProjError(e))
        }
    }

    override suspend fun getAvailableDates(officeId: Int): Result<WeekAvailabilityList> {
        Logger.i { "> getting dates | officeId: $officeId" }
        return try {
            Result.of(bookingApi.getAvailableDates(officeId))
        } catch (e: ResponseException) {
            Logger.e { e.message!! }
            Result.error(ErrorMapper.mapToProjError(e))
        }
    }

    override suspend fun getIslands(officeId: Int, date: String): Result<IslandList> {
        Logger.i { "> getting islands | officeId: $officeId, date: $date" }
        return try {
            Result.of(officeApi.getIslands(officeId, date))
        } catch (e: ResponseException) {
            Logger.e { e.message!! }
            Result.error(ErrorMapper.mapToProjError(e))
        }
    }

    override suspend fun registerUser(token: String): Result<RegisterUserResponse> {
        Logger.i { "> registering user | token: $token" }
        return try {
            Result.of(userApi.registerUser(token))
        } catch (e: ResponseException) {
            Logger.e { e.message!! }
            Result.error(ErrorMapper.mapToProjError(e))
        }
    }
}
