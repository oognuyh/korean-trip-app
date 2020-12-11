package com.example.trip.model.tour.common

data class TourResponse<T>(
        val header: TourHeader,
        val body: TourBody<T>
)