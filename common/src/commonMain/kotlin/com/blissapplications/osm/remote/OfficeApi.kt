package com.blissapplications.osm.remote

import com.blissapplications.osm.model.IslandList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

private const val BASE_URL = "https://bliss-desk.azurewebsites.net/api/v1/island"

class OfficeApi(private val client: HttpClient) : KoinComponent {

    suspend fun getIslands(id: Int, date:String): IslandList = client.get("$BASE_URL/$id"){
        parameter("date", date)
    }.body()
}