package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class Seat(
    val id: Int,
    val order: Int,
    val bookings: List<Reservation>? = null,
    val type: SeatType,
    val available: Boolean
) {
    fun hasBookings(): Boolean {
        return !bookings.isNullOrEmpty()
    }

    fun getPhoto(): String? {
        return bookings?.getOrNull(0)?.imageUrl
    }

    fun getSecondPhoto(): String? {
        return bookings?.getOrNull(1)?.imageUrl
    }

    val isFullyBooked: Boolean by lazy { bookings != null && (bookings.size == 2 || bookings.size == 1 && bookings.first().shift == Shift.FullDay) }
    val isMorningBooked: Boolean by lazy { bookings?.first()?.shift == Shift.Morning }
    val isAfternoonBooked: Boolean by lazy { bookings?.first()?.shift == Shift.Afternoon }

    val availableShifts: List<Shift> by lazy {
        when {
            isFullyBooked -> emptyList()
            isAfternoonBooked -> listOf(Shift.Morning)
            isMorningBooked -> listOf(Shift.Afternoon)
            else -> listOf(Shift.FullDay, Shift.Morning, Shift.Afternoon)
        }
    }
}

@Serializable
enum class
SeatType {
    Clear,
    PublicWorkStation,
    PrivateWorkStation
}