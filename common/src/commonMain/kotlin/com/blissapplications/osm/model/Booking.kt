package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val date: String,
    val seatId: Int,
    val shift: Shift,
    val note: String?,
)

@Serializable
enum class Shift {
    FullDay, Morning, Afternoon
}