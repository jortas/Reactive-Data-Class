package com.blissapplications.osm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    val id: Int,
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val avatarUrl: String
)