package com.blissapplications.osm.di

import com.blissapplications.osm.DiskStorage
import com.blissapplications.osm.PlatformDependencies
import com.blissapplications.osm.remote.BookingApi
import com.blissapplications.osm.remote.OfficeApi
import com.blissapplications.osm.remote.UserApi
import com.blissapplications.osm.managers.BlissDeskManager
import com.blissapplications.osm.managers.BlissDeskManagerFake
import com.blissapplications.osm.managers.SettingsManager
import com.blissapplications.osm.utils.ProjUserAgent
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs))
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = true) {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { createJson() }
    single { PlatformDependencies().userAgent }
    single { DiskStorage() }
    single { createHttpClient(get(), enableNetworkLogs = enableNetworkLogs, get()) }
    single { BookingApi(get()) }
    single { OfficeApi(get()) }
    single { UserApi(get()) }
    single { BlissDeskManager() }
    single { SettingsManager() }
    single { BlissDeskManagerFake() }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(
    json: Json,
    enableNetworkLogs: Boolean,
    projUserAgent: ProjUserAgent
): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
        // Full configuration.
        install(UserAgent) {
            agent = projUserAgent.platform
        }

        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.NONE
            }
        }
    }
}