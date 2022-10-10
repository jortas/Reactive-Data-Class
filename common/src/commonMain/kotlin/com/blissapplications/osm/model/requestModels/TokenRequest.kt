package com.blissapplications.osm.model.requestModels

import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    val token: String
)
