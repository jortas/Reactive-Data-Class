package com.blissapplications.osm.remote

import com.blissapplications.osm.model.*
import com.blissapplications.osm.model.requestModels.BookingRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent

private const val BASE_URL = "https://bliss-desk.azurewebsites.net/api/v1/booking"

class BookingApi(private val client: HttpClient) : KoinComponent {

    suspend fun bookSeat(
        token: String,
        bookingRequest: BookingRequest,
    ): Booking = client.post(BASE_URL) {
        headers {
            append(HttpHeaders.Authorization, "Bearer $token")
        }
        contentType(ContentType.Application.Json)
        setBody(bookingRequest)
    }.body()

    suspend fun deleteBooking(token: String, bookingId: Int): HttpResponse = client.delete("$BASE_URL/$bookingId") {
        headers {
            append(HttpHeaders.Authorization, "Bearer $token")
        }
    }

    suspend fun getAvailableDates(officeId: Int): WeekAvailabilityList =
        client.get("$BASE_URL/dates") {
            parameter("officeId", officeId)
        }.body()
}