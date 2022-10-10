package com.blissapplications.osm.errors.utils

import com.blissapplications.osm.errors.*
import io.ktor.client.plugins.*

import kotlin.reflect.KClass

object ErrorMapper {
    fun mapToProjError(exception: ResponseException): ProjError {
        return when (exception.message!!.extractErrorCodeFromMessage()) {
            400000 -> BadRequestError(exception.message, exception)
            400010 -> BadRequestError(exception.message, exception)
            400100 -> UnavailableDayError(exception.message, exception)
            400101 -> UserAlreadyBookedError(exception.message, exception)
            400102 -> UserMaxedBookingsError(exception.message, exception)
            400103 -> UnavailableSeatError(exception.message, exception)
            400104 -> ShiftAlreadyBookedError(exception.message, exception)
            400105 -> DayFullyBookedError(exception.message, exception)
            400106 -> NoAvailableSeatError(exception.message, exception)
            400107 -> InvalidDateError(exception.message, exception)
            401000 -> NoSessionError(exception.message, exception)
            404000 -> EntityNotFoundError(exception.message, exception)
            500000 -> InternalServerError(exception.message, exception)
            500001 -> DatabaseError(exception.message, exception)
            500002 -> DatabaseCreateError(exception.message, exception)
            500003 -> DatabaseDeleteError(exception.message, exception)
            500100 -> GoogleIdTokenError(exception.message, exception)
            else -> UnknownError(exception.message, exception)
        }
    }
}

fun String.extractErrorCodeFromMessage(): Int {
    return try {
        this.split("{\"code\":", limit = 2)[1].split(",", limit = 2)[0].toInt()
    } catch (e: Exception){
        0
    }
}