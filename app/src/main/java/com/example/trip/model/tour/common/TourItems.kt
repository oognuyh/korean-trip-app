package com.example.trip.model.tour.common

data class TourItems<T>(
    val item: T?,
    val items: List<T>?
)
