package com.example.trip.network

import com.example.trip.R
import com.example.trip.model.DetailInfoItems
import com.example.trip.utils.DetailInfoDeserializer
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TourAPIService {
    private val TOUR_API_BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/"
    private val TOUR_API_KEY = "NsIyBBw9ClLFRy0dl7G2RFkPd72rJS61GihChQZmtjCWXJadE%2Ff22gK%2Fy4zule0xN9YKkoC1cdY8IR1dwfK5Tw%3D%3D"

    fun getInstace(): TourAPI {
        val instance: TourAPI
        val gson = GsonBuilder().registerTypeAdapter(DetailInfoItems::class.java, DetailInfoDeserializer()).setLenient().create()

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

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        instance = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(TOUR_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TourAPI::class.java)

        return instance
    }
}