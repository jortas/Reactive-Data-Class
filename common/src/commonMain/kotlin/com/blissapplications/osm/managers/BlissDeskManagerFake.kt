package com.blissapplications.osm.managers

import com.blissapplications.osm.errors.utils.ProjError
import com.blissapplications.osm.model.*
import com.blissapplications.osm.model.requestModels.BookingRequest
import com.blissapplications.osm.model.requestModels.Result
import com.blissapplications.osm.model.SeatType
import com.blissapplications.osm.model.Seat
import com.blissapplications.osm.managers.BlissDeskManagerBehavior
import com.blissapplications.osm.model.responseModels.RegisterUserResponse
import io.ktor.client.statement.*
import org.koin.core.component.KoinComponent

class BlissDeskManagerFake: KoinComponent, BlissDeskManagerBehavior {
    private val fakeDate = "2022-01-01T00:00:00Z"

    override suspend fun bookSeat(token: String, bookingRequest: BookingRequest): Result<Booking> {
        return Result(Booking(fakeDate, 1, Shift.FullDay, null))
    }

    override suspend fun deleteBooking(token: String, bookingId: Int): Result<HttpResponse> {
        return Result.error(Error() as ProjError)
    }

    override suspend fun getAvailableDates(officeId: Int): Result<WeekAvailabilityList> {
        return Result(WeekAvailabilityList(
            listOf(WeekAvailability(
                listOf(
                    DayAvailability(10, 10, 0, true, "2022-01-01T00:00:00Z"),
                    DayAvailability(10, 10, 0, true, "2022-01-02T00:00:00Z"),
                    DayAvailability(10, 10, 0, true, "2022-01-03T00:00:00Z"),
                    DayAvailability(10, 10, 0, true, "2022-01-04T00:00:00Z"),
                    DayAvailability(10, 10, 0, true, "2022-01-05T00:00:00Z")
                )
            ))
        ))
    }

    override suspend fun getIslands(officeId: Int, date: String): Result<IslandList> {
        return Result(IslandList(listOf(
            Island("Island 0",
                0,
                "Island 0",
                listOf(
                    Seat(0, 0, null, SeatType.Clear, true),
                    Seat(1, 1, null, SeatType.Clear, true),
                    Seat(2, 2, null, SeatType.PublicWorkStation, true),
                    Seat(3, 3, null, SeatType.Clear, true),
                    Seat(4, 4, null, SeatType.Clear, true),
                    Seat(5, 5, null, SeatType.PublicWorkStation, true),
                    Seat(6, 6, null, SeatType.Clear, true),
                    Seat(7, 7, null, SeatType.Clear, true),
                    Seat(8, 8, null, SeatType.PublicWorkStation, true),
                    Seat(9, 9, null, SeatType.PublicWorkStation, true),
                )
            ),

            Island("Island 1",
                1,
                "Island 1",
                listOf(
                    Seat(0, 0, null, SeatType.Clear, true),
                    Seat(1, 1, null, SeatType.PublicWorkStation, true),
                    Seat(2, 2, null, SeatType.PublicWorkStation, true),
                    Seat(3, 3, null, SeatType.Clear, true),
                    Seat(4, 4, null, SeatType.Clear, true),
                    Seat(5, 5, null, SeatType.Clear, true),
                    Seat(6, 6, null, SeatType.PublicWorkStation, true),
                    Seat(7, 7, null, SeatType.PublicWorkStation, true),
                    Seat(8, 8, null, SeatType.Clear, true),
                    Seat(9, 9, null, SeatType.PublicWorkStation, true),
                )
            )
        )))
    }

    override suspend fun registerUser(token: String): Result<RegisterUserResponse> {
        return Result(RegisterUserResponse(
            "test@test.com",
            1,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1024px-User-avatar.svg.png",
            "fake user",
            0))
    }
}