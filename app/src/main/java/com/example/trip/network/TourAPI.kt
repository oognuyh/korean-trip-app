package com.example.trip.network

import com.example.trip.model.tour.common.TourItems
import com.example.trip.model.tour.common.TourVO
import com.example.trip.model.tour.Content
import com.example.trip.model.tour.ContentInfo
import com.example.trip.model.tour.ContentIntro
import com.example.trip.network.deserializer.TourItemsDeserializer
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface TourApi {
    /**
     * To get an Overview
     * Where to use - ContentDetailsFragment
     */
    @GET("detailCommon")
    suspend fun fetchContentBasicInfo(
        @Query("contentId") contentId: String,
        @Query("defaultYN") defaultYN: String = "Y",
        @Query("firstImageYN") firstImageYN: String = "Y",
        @Query("addrinfoYN") addrinfoYN: String = "Y",
        @Query("mapinfoYN") mapinfoYN: String = "Y",
        @Query("overviewYN") overviewYN: String = "Y"
    ): Response<TourVO<Content>>

    /**
     * To get All contents or get contents by contentTypeId
     * Where to use - HomeFragment
     */
    @GET("locationBasedList")
    suspend fun fetchAroundContents(
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("contentTypeId") contentTypeId: String?,
        @Query("mapX") mapX: String,
        @Query("mapY") mapY: String,
        @Query("radius") radius: String,
    ): Response<TourVO<Content>>

    /**
     * To get nearby contents by the user's current location
     * Where to use - AroundFragment
     */
    @GET("areaBasedList")
    suspend fun fetchContentList(
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("contentTypeId") contentTypeId: String,
        @Query("cat1") cat1: String?,
        @Query("cat2") cat2: String?,
    ): Response<TourVO<Content>>

    /**
     * To get a list of festivals
     * Where to use - HomeFragment
     */
    @GET("searchFestival")
    suspend fun fetchFestivalList(
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("eventStartDate") eventStartDate: String,
        @Query("eventEndDate") eventEndDate: String?
    ): Response<TourVO<Content>>

    /**
     * To get additional info like course details ..
     * Where to use - CourseDetailsFragment, ContentDetailsFragment
     */
    @GET("detailInfo")
    suspend fun fetchContentInfo(
        @Query("contentId") contentId: String,
        @Query("contentTypeId") contentTypeId: String
    ): Response<TourVO<ContentInfo>>

    /**
     * To get more specific info like course distance, time ..
     * Where to use - DetailsFragment
     */
    @GET("detailIntro")
    suspend fun fetchContentIntro(
        @Query("contentId") contentId: String,
        @Query("contentTypeId") contentTypeId: String
    ): Response<TourVO<ContentIntro>>

    companion object {
        private const val TOUR_API_BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/"
        private const val TOUR_API_KEY = "NsIyBBw9ClLFRy0dl7G2RFkPd72rJS61GihChQZmtjCWXJadE%2Ff22gK%2Fy4zule0xN9YKkoC1cdY8IR1dwfK5Tw%3D%3D"

        fun invoke(): TourApi {
            val gson = GsonBuilder().registerTypeAdapter(TourItems::class.java, TourItemsDeserializer<Content>()).setLenient().create()
            val interceptor = Interceptor { chain ->
                var request = chain.request()
                val url = request.url()
                    .newBuilder()
                    .addEncodedQueryParameter("ServiceKey", TOUR_API_KEY)
                    .addEncodedQueryParameter("MobileOS", "ETC")
                    .addEncodedQueryParameter("MobileApp", "AppTest")
                    .addEncodedQueryParameter("_type", "json")
                    .build()

                request = request
                    .newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(TOUR_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TourApi::class.java)
        }
    }
}