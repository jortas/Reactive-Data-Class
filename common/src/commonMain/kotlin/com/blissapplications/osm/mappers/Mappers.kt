package com.blissapplications.osm.mappers

import com.blissapplications.osm.model.DayAvailability
import com.blissapplications.osm.model.Island
import com.blissapplications.osm.model.Seat
import com.blissapplications.osm.model.WeekAvailability

fun Island.mapToTablePairs(): List<Pair<Seat, Seat>> {
    val oddSeats = this.seats.filter { it.order.isOdd() }
    val evensSeats = this.seats.filter { !it.order.isOdd() }

    return oddSeats.zip(evensSeats) { oddSeat, evenSeat ->
        Pair(oddSeat, evenSeat)
    }
}

fun List<WeekAvailability>.mapToDaysList(): List<DayAvailability> {
    val list = mutableListOf<DayAvailability>()
    this.forEach {
        list.addAll(it.days)
    }
    return list
}

fun Int.isOdd(): Boolean {
    return this.rem(2) == 1
}