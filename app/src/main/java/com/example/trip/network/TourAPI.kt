package com.example.trip.network

import androidx.lifecycle.LiveData
import com.example.trip.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TourAPI {
    /* 공통정보조회 */
    @GET("detailCommon")
    suspend fun getDetailCommon(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("contentId", encoded = true) contentId: String,
    ): Response<DetailCommonVO>

    /* 위치기반 관광정보조회 */
    @GET("locationBasedList")
    suspend fun getLocationBasedList(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("pageNo", encoded = true) pageNo: String,
        @Query("mapX", encoded = true) mapX: String,
        @Query("mapY", encoded = true) mapY: String,
        @Query("radius", encoded = true) radius: String = "5000",
    ): Response<LocationBasedVO>

    /* 지역기반 관광정보조회 */
    @GET("areaBasedList")
    suspend fun getAreaBasedList(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("pageNo", encoded = true) pageNo: String,
        @Query("arrange", encoded = true) arrange: String? = null,
        @Query("contentTypeId", encoded = true) contentTypeId: String? = null
    ): Response<AreaBasedVO>

    /* 행사정보조회 */
    @GET("searchFestival")
    suspend fun getSearchFestival(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("pageNo", encoded = true) pageNo: String,
        @Query("arrange", encoded = true) arrange: String = "P", // O = 제목순, P = 조회순, Q = 수정일순, R = 생성일순
        @Query("eventStartDate", encoded = true) eventStartDate: String,
        @Query("eventEndDate", encoded = true) eventEndDate: String? = null
    ): Response<SearchFestivalVO>

    /* 반복정보조회 */
    @GET("detailInfo")
    suspend fun getDetailInfo(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("contentId", encoded = true) contentId: String,
        @Query("contentTypeId", encoded = true) contentTypeId: String
    ): Response<DetailInfoVO>

    /* 소개정보조회 */
    @GET("detailIntro")
    suspend fun getDetailIntro(
        @Query("numOfRows", encoded = true) numOfRows: String = NUM_OF_ROWS,
        @Query("contentId", encoded = true) contentId: String,
        @Query("contentTypeId", encoded = true) contentTypeId: String
    ): Response<DetailIntroVO>
}

private const val NUM_OF_ROWS = "10"
