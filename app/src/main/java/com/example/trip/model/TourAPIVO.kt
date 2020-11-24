package com.example.trip.model

import com.google.gson.annotations.SerializedName

data class AreaBasedVO(@SerializedName("response") val response: AreaBasedResponse)
data class AreaBasedResponse(@SerializedName("header") val header: AreaBasedHeader, @SerializedName("body") val body: AreaBasedBody)
data class AreaBasedHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class AreaBasedBody(@SerializedName("items") val items: AreaBasedItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class AreaBasedItems(@SerializedName("item") val contents: List<AreaBasedContent>?)

data class DetailCommonVO(@SerializedName("response") val response: DetailCommonResponse)
data class DetailCommonResponse(@SerializedName("header") val header: DetailCommonHeader, @SerializedName("body") val body: DetailCommonBody)
data class DetailCommonHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class DetailCommonBody(@SerializedName("items") val items: DetailCommonItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class DetailCommonItems(@SerializedName("item") val contents: List<DetailCommonContent>?)

data class DetailInfoVO(@SerializedName("response") val response: DetailInfoResponse)
data class DetailInfoResponse(@SerializedName("header") val header: DetailInfoHeader, @SerializedName("body") val body: DetailInfoBody)
data class DetailInfoHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class DetailInfoBody(@SerializedName("items") val items: DetailInfoItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class DetailInfoItems(@SerializedName("item") val contents: Any)

data class DetailIntroVO(@SerializedName("response") val response: DetailIntroResponse)
data class DetailIntroResponse(@SerializedName("header") val header: DetailIntroHeader, @SerializedName("body") val body: DetailIntroBody)
data class DetailIntroHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class DetailIntroBody(@SerializedName("items") val items: DetailIntroItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class DetailIntroItems(@SerializedName("item") val contents: DetailIntroContent)

data class LocationBasedVO(@SerializedName("response") val response: LocationBasedResponse)
data class LocationBasedResponse(@SerializedName("header") val header: LocationBasedHeader, @SerializedName("body") val body: LocationBasedBody)
data class LocationBasedHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class LocationBasedBody(@SerializedName("items") val items: LocationBasedItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class LocationBasedItems(@SerializedName("item") val contents: List<LocationBasedContent>)

data class SearchFestivalVO(@SerializedName("response") val response: SearchFestivalResponse)
data class SearchFestivalResponse(@SerializedName("header") val header: SearchFestivalHeader, @SerializedName("body") val body: SearchFestivalBody)
data class SearchFestivalHeader(@SerializedName("resultCode") val resultCode: String, @SerializedName("resultMsg") val resultMsg: String)
data class SearchFestivalBody(@SerializedName("items") val items: SearchFestivalItems, @SerializedName("numOfRows") val numOfRows: Long, @SerializedName("pageNo") val pageNo: Long, @SerializedName("totalCount") val totalCount: Long)
data class SearchFestivalItems(@SerializedName("item") val contents: List<SearchFestivalContent>)