package com.example.trip.model.tour.common

import com.google.gson.annotations.SerializedName

data class TourVO<T>(
        @SerializedName("response")
        val response: TourResponse<T>?
)