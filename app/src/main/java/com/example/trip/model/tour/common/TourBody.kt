package com.example.trip.model.tour.common

import com.google.gson.annotations.SerializedName

data class TourBody<T>(
        @SerializedName("items")
    val items: TourItems<T>,
        @SerializedName("numOfRows")
    val numOfRows: Int,
        @SerializedName("pageNo")
    val pageNo: Int,
        @SerializedName("totalCount")
    val totalCount: Int
)