package com.example.trip.model.tour.common

import com.google.gson.annotations.SerializedName

data class TourHeader(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)
