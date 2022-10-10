package com.blissapplications.osm.model

import kotlinx.serialization.Serializable

@Serializable
data class Office (
    val id: Int,
    val floor: String,
    val location: String,
    val name: String,
)