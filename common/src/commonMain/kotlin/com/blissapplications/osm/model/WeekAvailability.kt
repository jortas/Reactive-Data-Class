package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class WeekAvailability(
    val days: List<DayAvailability>
)

@Serializable
data class WeekAvailabilityList(
    val list: List<WeekAvailability>,
)