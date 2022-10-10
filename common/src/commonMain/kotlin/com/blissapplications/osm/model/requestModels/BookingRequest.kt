package com.blissapplications.osm.model.requestModels

import com.blissapplications.osm.model.Shift
import kotlinx.serialization.Serializable

@Serializable
data class BookingRequest(
    val date: String,
    val officeId: Int?,
    val seatId: Int?,
    val shift: Shift,
    val note: String,
    val randomizeSeats: Boolean = false
)