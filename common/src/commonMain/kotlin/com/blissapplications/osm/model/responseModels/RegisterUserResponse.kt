package com.blissapplications.osm.model.responseModels

import kotlinx.serialization.Serializable

@Serializable
 data class RegisterUserResponse(
    val email:String,
    val id: Int,
    val imageUrl: String,
    val name : String,
    val weeklyBookings: Int
)