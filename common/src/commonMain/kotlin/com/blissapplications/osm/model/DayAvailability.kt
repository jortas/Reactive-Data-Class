package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class DayAvailability(
    val availableBookings: Int,
    val availableSeats: Int,
    val bookings: Int = 0,
    val canBook: Boolean,
    val date: String,
)