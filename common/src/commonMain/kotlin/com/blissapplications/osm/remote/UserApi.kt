package com.blissapplications.osm.remote

import com.blissapplications.osm.model.requestModels.TokenRequest
import com.blissapplications.osm.model.responseModels.RegisterUserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

private const val BASE_URL = "https://bliss-desk.azurewebsites.net/api/v1/user"

class UserApi(private val client: HttpClient) : KoinComponent {

    suspend fun registerUser(token: String): RegisterUserResponse = client.post(BASE_URL){
        contentType(ContentType.Application.Json)
        setBody(TokenRequest(token))
    }.body()
}