package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class Island(
    val name: String,
    val order: Int,
    val description: String = "",
    val seats: List<Seat>,
)

@Serializable
data class IslandList(
    val list: List<Island>
)