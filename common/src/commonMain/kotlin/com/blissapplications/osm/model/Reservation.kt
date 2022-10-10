package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class Reservation(
    val id: Int,
    val userId: Int,
    val name: String,
    val email: String,
    val imageUrl: String,
    val shift: Shift,
)