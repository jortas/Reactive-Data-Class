package com.blissapplications.osm

import com.blissapplications.osm.model.*
import com.blissapplications.osm.model.requestModels.BookingRequest

object MockData {
    val weekAvailabilityList: WeekAvailabilityList =
        WeekAvailabilityList(
            mutableListOf(
                WeekAvailability(
                    mutableListOf(
                        DayAvailability(
                            2,
                            2,
                            2,
                            false,
                            "2020-12-12"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-13"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-14"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-15"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-16"
                        ),
                    )
                ),
                WeekAvailability(
                    mutableListOf(
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-12"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-13"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-14"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-15"
                        ),
                        DayAvailability(
                            2,
                            2,
                            2,
                            true,
                            "2020-12-16"
                        ),
                    )
                )
            )
        )

    val daysStringList: List<String> =
        listOf("12/10","13/10","14/10")

    val shiftsStringList: List<String> =
        listOf("todo o dia", "tarde")

    val islands: List<Island> = listOf(
        Island(
            name = "A",
            order = 1,
            description = "Boa Ilha",
            seats = listOf(
                Seat(
                    id = 0,
                    order = 1,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 1,
                    order = 2,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 3,
                    order = 4,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 4,
                    order = 5,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 5,
                    order = 6,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 7,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
            )
        ),
        Island(
            name = "B",
            order = 2,
            description = "Boa Ilha",
            seats = listOf(
                Seat(
                    id = 0,
                    order = 1,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 1,
                    order = 2,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 3,
                    order = 4,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 4,
                    order = 5,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 5,
                    order = 6,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 7,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 3,
                    order = 8,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 4,
                    order = 9,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 5,
                    order = 10,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 11,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 3,
                    order = 12,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 4,
                    order = 13,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 5,
                    order = 14,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 15,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 16,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
                Seat(
                    id = 6,
                    order = 17,
                    bookings = null,

                    type = SeatType.Clear,
                    available = true
                ),
            )
        ),
    )

    val islandList: IslandList = IslandList(islands)

    val officeLisboa: Office = Office(
        id = 1,
        floor = "",
        location = "",
        name = "Santo Amaro, Oeiras",
    )

    val officePorto: Office = Office(
        id = 2,
        floor = "",
        location = "",
        name = "Porto",
    )

    val offices = listOf(officeLisboa, officePorto)

    val seat: Seat =
        Seat(
            id = 0,
            order = 1,
            bookings = null,

            type = SeatType.Clear,
            available = true
        )

    val bookRequest: BookingRequest =
        BookingRequest(date = "2021-06-18T00:12:32Z", 1, null, Shift.FullDay, "pa", true)
}

