package com.blissapplications.osm.managers

import com.blissapplications.osm.model.Booking
import com.blissapplications.osm.model.IslandList
import com.blissapplications.osm.model.Office
import com.blissapplications.osm.model.WeekAvailabilityList
import com.blissapplications.osm.model.requestModels.BookingRequest
import com.blissapplications.osm.model.requestModels.Result
import com.blissapplications.osm.model.responseModels.RegisterUserResponse
import io.ktor.client.statement.*

interface BlissDeskManagerBehavior {
    suspend fun bookSeat(token: String,  bookingRequest: BookingRequest): Result<Booking>
    suspend fun deleteBooking(token: String, bookingId: Int): Result<HttpResponse>
    suspend fun getAvailableDates(officeId: Int = 1): Result<WeekAvailabilityList>
    suspend fun getIslands(officeId: Int = 1, date: String): Result<IslandList>
    suspend fun registerUser(token: String): Result<RegisterUserResponse>
}